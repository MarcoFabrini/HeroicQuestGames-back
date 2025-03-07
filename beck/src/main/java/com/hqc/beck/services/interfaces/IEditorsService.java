package com.hqc.beck.services.interfaces;

import java.util.List;

import com.hqc.beck.dto.EditorsDTO;
import com.hqc.beck.request.EditorsRequest;

public interface IEditorsService {

    List<EditorsDTO> list() throws Exception;

    // List<EditorsDTO> searchByTyping(Integer id, String name, String website) throws Exception;

    void create(EditorsRequest req) throws Exception;

    void update(EditorsRequest req) throws Exception;

    void delete(Integer id) throws Exception;

}// interfaces
