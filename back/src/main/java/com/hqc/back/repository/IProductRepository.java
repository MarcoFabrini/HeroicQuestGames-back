package com.hqc.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hqc.back.model.Product;


@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByNameAndIdNot(String name, Integer id);

    @Query(name = "product.searchByName")
    List<Product> searchByName( @Param("name") String name);

}// interface
