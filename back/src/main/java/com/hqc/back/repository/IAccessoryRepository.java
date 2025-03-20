package com.hqc.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hqc.back.model.Accessory;

@Repository
public interface IAccessoryRepository extends JpaRepository<Accessory, Integer> {

}// interface
