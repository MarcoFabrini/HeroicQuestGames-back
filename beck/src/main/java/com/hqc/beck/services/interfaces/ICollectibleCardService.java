package com.hqc.beck.services.interfaces;

import com.hqc.beck.request.CollectibleCardRequest;

public interface ICollectibleCardService {
    void create(CollectibleCardRequest req) throws Exception;

    void update(CollectibleCardRequest req) throws Exception;

}// interface
