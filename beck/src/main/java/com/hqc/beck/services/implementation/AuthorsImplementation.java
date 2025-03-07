package com.hqc.beck.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqc.beck.dto.AuthorsDTO;
import com.hqc.beck.model.Authors;
import com.hqc.beck.repository.IAuthorsRepository;
import com.hqc.beck.repository.IProductRepository;
import com.hqc.beck.request.AuthorsRequest;
import com.hqc.beck.services.interfaces.IAuthorsService;
import static com.hqc.beck.utils.Utilities.buildAuthorsDTO;

@Service
public class AuthorsImplementation implements IAuthorsService {

    private IAuthorsRepository authorsRepository;
    private IProductRepository productRepository;
    private Logger log;

    public AuthorsImplementation(IAuthorsRepository authorsRepository, Logger log, IProductRepository productRepository) {
        this.authorsRepository = authorsRepository;
        this.productRepository = productRepository;
        this.log = log;
    }

    // @Override
    // public List<AuthorsDTO> searchByTyping(Integer id, String name, String
    // lastname, String country, String biography)
    // throws Exception {
    // log.debug("Cercando lista di autori con filtri");
    // List<Authors> listAuthors = authorsRepository.searchByFilter(id, name,
    // lastname, country, biography);
    // log.debug("List SearchByTyping: " + listAuthors);
    // return buildAuthorsDTO(listAuthors);
    // }// List Search

    @Override
    public List<AuthorsDTO> list() throws Exception {
        List<Authors> listAuthors = authorsRepository.findAll();
        return buildAuthorsDTO(listAuthors);
    }// list

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(AuthorsRequest req) throws Exception {
        Optional<Authors> authors = authorsRepository.findByNameAndLastname(req.getName(), req.getLastname());
        if (authors.isPresent())
            throw new Exception("authors-isPresent");

        Authors a = new Authors();
        a.setName(req.getName());
        a.setLastname(req.getLastname());
        a.setCountry(req.getCountry());
        a.setBiography(req.getBiography());
        a.setActive(true);

        authorsRepository.save(a);
    }// create

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AuthorsRequest req) throws Exception {
        Optional<Authors> a = authorsRepository.findById(req.getId());
        if (!a.isPresent())
            throw new Exception("authors-isNotPresent");

        if (authorsRepository.findByNameAndLastnameAndIdNot(req.getName(), req.getLastname(), req.getId()).isPresent())
            throw new Exception("authors present");

        a.get().setName(req.getName());
        a.get().setLastname(req.getLastname());
        a.get().setCountry(req.getCountry());
        a.get().setBiography(req.getBiography());
        a.get().setActive(req.getActive());

        authorsRepository.save(a.get());
    }// update

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) throws Exception {
        Optional<Authors> a = authorsRepository.findById(id);
        if (!a.isPresent())
            throw new Exception("authors-isNotPresent");

        a.get().setActive(false);

        authorsRepository.save(a.get());
    }// delete

}// class
