package com.hqc.back.services.interfaces;

import java.util.List;

import com.hqc.back.dto.AuthorsDTO;
import com.hqc.back.request.AuthorsRequest;

public interface IAuthorsService {

    List<AuthorsDTO> list() throws Exception;

    // List<AuthorsDTO> searchByTyping(Integer id, String name, String lastname,
    // String country, String biography) throws Exception;

    void create(AuthorsRequest req) throws Exception;

    void update(AuthorsRequest req) throws Exception;

    void delete(Integer id) throws Exception;

}// interfaces
