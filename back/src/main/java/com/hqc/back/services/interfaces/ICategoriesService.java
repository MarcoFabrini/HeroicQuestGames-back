package com.hqc.back.services.interfaces;

import java.util.List;

import com.hqc.back.dto.CategoriesDTO;
import com.hqc.back.request.CategoriesRequest;

public interface ICategoriesService {

    List<CategoriesDTO> list() throws Exception;

    // List<CategoriesDTO> searchByTyping(Integer id, String name) throws Exception;

    void create(CategoriesRequest req) throws Exception;

    void update(CategoriesRequest req) throws Exception;

    void delete(Integer id) throws Exception;

}// interfaces
