package com.hqc.beck.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqc.beck.dto.CartsDTO;
import com.hqc.beck.model.Carts;
import com.hqc.beck.repository.ICartsRepository;
import com.hqc.beck.services.interfaces.ICartsService;
import static com.hqc.beck.utils.Utilities.buildCartsDTO;

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
