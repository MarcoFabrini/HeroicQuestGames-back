package com.hqc.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hqc.back.model.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {
	Optional<Users> findByEmail(String email);

	List<Users> findAllByIdNot(Integer id);

}// interface
