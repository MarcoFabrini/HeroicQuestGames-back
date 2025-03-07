package com.hqc.beck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hqc.beck.model.DetailsShipping;

@Repository
public interface IDetailsShippingsRepository extends JpaRepository<DetailsShipping, Integer>{
	
}// interface
