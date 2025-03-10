package com.hqc.beck.services.interfaces;

import com.hqc.beck.dto.AccessoryDTO;
import com.hqc.beck.request.AccessoryRequest;

public interface IAccessoryService {
    void create(AccessoryRequest req) throws Exception;

    AccessoryDTO listById(Integer id) throws Exception;

}// interface
