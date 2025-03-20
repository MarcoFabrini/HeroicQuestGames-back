package com.hqc.back.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqc.back.dto.CartsDTO;
import com.hqc.back.model.Carts;
import com.hqc.back.repository.ICartsRepository;
import com.hqc.back.services.interfaces.ICartsService;
import static com.hqc.back.utils.Utilities.buildCartsDTO;

@Service
public class CartsImplementation implements ICartsService{
    @Autowired
    ICartsRepository cartR;

    @Override
	public List<CartsDTO> list() throws Exception {
		List<Carts> lC = cartR.findAll();
        
		return buildCartsDTO(lC);
	}
    
}// class
