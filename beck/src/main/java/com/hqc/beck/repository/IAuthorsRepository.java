package com.hqc.beck.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hqc.beck.model.Authors;

@Repository
public interface IAuthorsRepository extends JpaRepository<Authors, Integer> {

	// @Query(name = "authors.searchByTyping")
	// List<Authors> searchByFilter(
	// @Param("id") Integer id,
	// @Param("name") String name,
	// @Param("lastname") String lastname,
	// @Param("country") String country,
	// @Param("biography") String biography
	// );

	Optional<Authors> findByNameAndLastname(String name, String lastname);

	Optional<Authors> findByNameAndLastnameAndIdNot(String name, String lastname, Integer id);
}// interface
