package com.hqc.beck.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqc.beck.dto.DetailsShippingDTO;
import com.hqc.beck.model.DetailsShipping;
import com.hqc.beck.model.Users;
import com.hqc.beck.repository.IDetailsShippingsRepository;
import com.hqc.beck.repository.UsersRepository;
import com.hqc.beck.request.DetailsShippingRequest;
import com.hqc.beck.services.interfaces.IDetailsShippingService;
import static com.hqc.beck.utils.Utilities.buildDetailsShippingDTO;

@Service
public class DetailsShippingImplementation implements IDetailsShippingService {

	@Autowired
	Logger log;
	@Autowired
	IDetailsShippingsRepository detailsShippingsRepository;
	@Autowired
	UsersRepository usersRepository;

	@Override
	public List<DetailsShippingDTO> listByUserId(Integer id) throws Exception {
		Optional<Users> user = usersRepository.findById(id);
		if (!user.isPresent())
			throw new Exception("user-noPresent");

		return buildDetailsShippingDTO(user.get().getListDetailsShippings());
	}// list

	@Override
	public void create(DetailsShippingRequest req) throws Exception {
		Optional<Users> user = usersRepository.findById(req.getUserId());
		if (!user.isPresent())
			throw new Exception("user-noPresent");

		DetailsShipping ds = new DetailsShipping();
		ds.setName(req.getName());
		ds.setLastname(req.getLastname());
		ds.setCountry(req.getCountry());
		ds.setStateRegion(req.getStateRegion());
		ds.setCap(req.getCap());
		ds.setCity(req.getCity());
		ds.setAddress(req.getAddress());
		ds.setUser(user.get());
		ds.setActive(true);

		detailsShippingsRepository.save(ds);
	}// create

	@Override
	public void update(DetailsShippingRequest req) throws Exception {
		Optional<DetailsShipping> ds = detailsShippingsRepository.findById(req.getId());
		if (!ds.isPresent())
			throw new Exception("detailsShipping-noPresent");

		ds.get().setName(req.getName());
		ds.get().setLastname(req.getLastname());
		ds.get().setCountry(req.getCountry());
		ds.get().setStateRegion(req.getStateRegion());
		ds.get().setCap(req.getCap());
		ds.get().setCity(req.getCity());
		ds.get().setAddress(req.getAddress());
		ds.get().setActive(req.getActive());

		detailsShippingsRepository.save(ds.get());
	}// update

	@Override
	public void delete(Integer id) throws Exception {
		Optional<DetailsShipping> ds = detailsShippingsRepository.findById(id);
		if (!ds.isPresent())
			throw new Exception("detailsShipping-noPresent");

		ds.get().setActive(false);
		detailsShippingsRepository.save(ds.get());
	}// delete

}// class
