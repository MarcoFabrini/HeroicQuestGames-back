package com.hqc.back.services.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqc.back.dto.CategoriesDTO;
import com.hqc.back.model.Categories;
import com.hqc.back.repository.ICategoriesRepository;
import com.hqc.back.repository.IProductRepository;
import com.hqc.back.request.CategoriesRequest;
import com.hqc.back.services.interfaces.ICategoriesService;
import static com.hqc.back.utils.Utilities.buildCategoriesDTO;



@Service
public class CategoriesImplementation implements ICategoriesService {
    private ICategoriesRepository categoriesRepository;
    private IProductRepository productRepository;
    private Logger log;

    public CategoriesImplementation(ICategoriesRepository categoriesRepository, Logger log,
    IProductRepository productRepository) {
        this.categoriesRepository = categoriesRepository;
        this.log = log;
        this.productRepository = productRepository;
    }

    @Override
    public List<CategoriesDTO> list() throws Exception {
        List<Categories> listCategories = categoriesRepository.findAll();
        return buildCategoriesDTO(listCategories);
    }// List

    // @Override
    // public List<CategoriesDTO> searchByTyping(Integer id, String name) throws
    // Exception {
    // List<Categories> listCategories = categoriesRepository.searchByFilter(id,
    // name);
    // return buildCategoriesDTO(listCategories);
    // }// SearchByTyping

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CategoriesRequest req) throws Exception {

    }// Create

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CategoriesRequest req) throws Exception {

    }// Update

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) throws Exception {

    }// Delete

}// class
