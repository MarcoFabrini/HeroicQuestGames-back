package com.hqc.back.services.interfaces;

import com.hqc.back.request.AccessoryRequest;

public interface IAccessoryService {
    void create(AccessoryRequest req) throws Exception;

    void update(AccessoryRequest req) throws Exception;

}// interface
