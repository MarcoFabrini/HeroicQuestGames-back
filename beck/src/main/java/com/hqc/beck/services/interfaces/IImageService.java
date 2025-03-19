package com.hqc.beck.services.interfaces;

import java.util.List;

import org.springframework.core.io.Resource;

import com.hqc.beck.request.ImageRequest;

public interface IImageService {
    void uploadImage(ImageRequest req) throws Exception;

    List<String> getImagesByProductId(ImageRequest req) throws Exception;

    Resource getImage(String filename) throws Exception;
}// interface
