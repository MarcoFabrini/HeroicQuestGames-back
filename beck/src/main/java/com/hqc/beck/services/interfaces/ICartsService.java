package com.hqc.beck.services.interfaces;

import java.util.List;

import com.hqc.beck.dto.CartsDTO;

public interface ICartsService {

    List<CartsDTO> list() throws Exception;

}// interface
