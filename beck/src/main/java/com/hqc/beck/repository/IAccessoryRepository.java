package com.hqc.beck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hqc.beck.model.Accessory;

@Repository
public interface IAccessoryRepository extends JpaRepository<Accessory, Integer> {

}// interface
