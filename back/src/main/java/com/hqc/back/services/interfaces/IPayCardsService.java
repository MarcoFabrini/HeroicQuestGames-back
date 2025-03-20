package com.hqc.back.services.interfaces;

import java.util.List;

import com.hqc.back.dto.PayCardsDTO;
import com.hqc.back.request.PayCardsRequest;

public interface IPayCardsService {

    List<PayCardsDTO> listByUserId(Integer id) throws Exception;

    void create(PayCardsRequest req) throws Exception;

    void update(PayCardsRequest req) throws Exception;

    void delete(Integer id) throws Exception;

}// interface
