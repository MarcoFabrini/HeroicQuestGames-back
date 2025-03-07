package com.hqc.beck.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hqc.beck.model.Product;


@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByNameAndIdNot(String name, Integer id);

    // @Query(name = "games.searchByTyping")
    // List<Games> searchByTyping( @Param("gameName") String name,
    // @Param("authorId") Integer authorsId,
    // @Param("categoryId") Integer categoriesId,
    // @Param("editorId") Integer editorId);

}// interface
