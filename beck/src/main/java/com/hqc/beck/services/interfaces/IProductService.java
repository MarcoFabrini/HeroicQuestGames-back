package com.hqc.beck.services.interfaces;

import java.util.List;

import com.hqc.beck.dto.ProductDTO;
import com.hqc.beck.request.ProductRequest;

public interface IProductService {

    List<ProductDTO> list() throws Exception;

    ProductDTO getProductById(Integer id) throws Exception;

    // List<GamesDTO> searchByTyping(String name, Integer authorsId, Integer
    // categoriesId, Integer editorId) throws Exception;

    void create(ProductRequest req) throws Exception;

    void update(ProductRequest req) throws Exception;

    void delete(Integer id) throws Exception;

}// interface
