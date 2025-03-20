package com.hqc.back.services.interfaces;

import java.util.List;

import com.hqc.back.dto.DetailsOrderDTO;

public interface IDetailsOrderService {

    List<DetailsOrderDTO> searchByOrder(Integer id) throws Exception;

}
