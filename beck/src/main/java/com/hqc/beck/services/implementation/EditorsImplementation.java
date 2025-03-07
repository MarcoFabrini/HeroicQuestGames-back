package com.hqc.beck.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqc.beck.dto.EditorsDTO;
import com.hqc.beck.model.Editors;
import com.hqc.beck.repository.IEditorsRepository;
import com.hqc.beck.request.EditorsRequest;
import com.hqc.beck.services.interfaces.IEditorsService;
import static com.hqc.beck.utils.Utilities.buildEditorsDTO;

@Service
public class EditorsImplementation implements IEditorsService {
    @Autowired
    IEditorsRepository editorsRepository;

    @Override
    public List<EditorsDTO> list() throws Exception {
        List<Editors> listEditors = editorsRepository.findAll();
        return buildEditorsDTO(listEditors);
    }// list

    // @Override
    // public List<EditorsDTO> searchByTyping(Integer id, String name, String website) throws Exception {
    //     List<Editors> listEditors = editorsRepository.searchByTyping(id, name, website);
    //     return buildEditorsDTO(listEditors);
    // }// searchByTyping

    @Override
    public void create(EditorsRequest req) throws Exception {
        Optional<Editors> editors = editorsRepository.findByNameAndWebsite(req.getName(), req.getWebsite());
        if (editors.isPresent())
            throw new Exception("editors-Present");

        Editors e = new Editors();
        e.setName(req.getName());
        e.setWebsite(req.getWebsite());
        e.setActive(true);

        editorsRepository.save(e);
    }// create

    @Override
    public void update(EditorsRequest req) throws Exception {
        Optional<Editors> editors = editorsRepository.findById(req.getId());
        if (!editors.isPresent())
            throw new Exception("editor-noPresent");

        if (editorsRepository.findByNameAndIdNot(req.getName(), req.getId()).isPresent())
            throw new Exception("editors-Present");

        if (editorsRepository.findByWebsiteAndIdNot(req.getWebsite(), req.getId()).isPresent())
            throw new Exception("editorsWeb-Present");

        editors.get().setName(req.getName());
        editors.get().setWebsite(req.getWebsite());
        editors.get().setActive(req.getActive());

        editorsRepository.save(editors.get());
    }// update

    @Override
    public void delete(Integer id) throws Exception {
        Optional<Editors> editors = editorsRepository.findById(id);
        if (!editors.isPresent())
            throw new Exception("editor-noPresent");

        editors.get().setActive(false);

        editorsRepository.save(editors.get());
    }// delete

}// class
