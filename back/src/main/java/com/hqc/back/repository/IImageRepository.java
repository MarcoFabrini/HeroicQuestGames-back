package com.hqc.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hqc.back.model.Image;

public interface IImageRepository extends JpaRepository<Image, Integer>{
    List<Image> findByProductId(Integer productId);
}// interface
