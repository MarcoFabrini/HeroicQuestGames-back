package com.hqc.beck.services.interfaces;

import java.util.List;

import com.hqc.beck.dto.ProductDTO;

public interface IProductService {

    List<ProductDTO> list() throws Exception;

    List<ProductDTO> searchByName(String name) throws Exception;

    ProductDTO getProductById(Integer id) throws Exception;

    void delete(Integer id) throws Exception;

}// interface
