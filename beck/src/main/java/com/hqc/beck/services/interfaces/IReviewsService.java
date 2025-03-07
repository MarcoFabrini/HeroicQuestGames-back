package com.hqc.beck.services.interfaces;

import java.util.List;

import com.hqc.beck.dto.ReviewsDTO;
import com.hqc.beck.request.ReviewsRequest;

public interface IReviewsService {

    List<ReviewsDTO> listByProductId(Integer idGame);

    void create(ReviewsRequest req) throws Exception;

    void update(ReviewsRequest req) throws Exception;

    void delete(Integer id) throws Exception;
}// interface
