package com.hqc.back.services.interfaces;

import java.util.List;

import com.hqc.back.dto.CartsDTO;

public interface ICartsService {

    List<CartsDTO> list() throws Exception;

}// interface
