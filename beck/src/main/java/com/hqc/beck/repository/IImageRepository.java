package com.hqc.beck.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hqc.beck.model.Image;

public interface IImageRepository extends JpaRepository<Image, Integer>{
    List<Image> findByProductId(Integer productId);
}// interface
