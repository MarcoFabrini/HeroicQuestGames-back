package com.hqc.beck.services.interfaces;

import java.util.List;

import com.hqc.beck.dto.PayCardsDTO;
import com.hqc.beck.request.PayCardsRequest;

public interface IPayCardsService {

    List<PayCardsDTO> listByUserId(Integer id) throws Exception;

    void create(PayCardsRequest req) throws Exception;

    void update(PayCardsRequest req) throws Exception;

    void delete(Integer id) throws Exception;

}// interface
