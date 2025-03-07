package com.hqc.beck.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hqc.beck.model.Categories;

@Repository
public interface ICategoriesRepository extends JpaRepository<Categories, Integer> {
    Optional<Categories> findByName(String name);
    
    // @Query(name = "category.searchByTyping")
	// List<Categories> searchByFilter(
 	// 		@Param("id") Integer id,
 	// 		@Param("name") String name);
}// interface
