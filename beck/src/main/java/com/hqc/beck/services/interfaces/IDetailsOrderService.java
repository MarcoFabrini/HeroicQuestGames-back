package com.hqc.beck.services.interfaces;

import java.util.List;

import com.hqc.beck.dto.DetailsOrderDTO;

public interface IDetailsOrderService {

    List<DetailsOrderDTO> searchByOrder(Integer id) throws Exception;

}
