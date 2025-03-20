package com.hqc.back.services.interfaces;

import java.util.List;

import com.hqc.back.dto.DetailsShippingDTO;
import com.hqc.back.request.DetailsShippingRequest;

public interface IDetailsShippingService {
	
	List<DetailsShippingDTO> listByUserId(Integer id) throws Exception;
	
	void create(DetailsShippingRequest req) throws Exception;
	
	void update(DetailsShippingRequest req) throws Exception;

	void delete(Integer id) throws Exception;
}// interface 
