package com.hqc.back.services.interfaces;

import com.hqc.back.request.CollectibleCardRequest;

public interface ICollectibleCardService {
    void create(CollectibleCardRequest req) throws Exception;

    void update(CollectibleCardRequest req) throws Exception;

}// interface
