package com.hqc.beck.services.interfaces;

import com.hqc.beck.request.AccessoryRequest;

public interface IAccessoryService {
    void create(AccessoryRequest req) throws Exception;

    void update(AccessoryRequest req) throws Exception;

}// interface
