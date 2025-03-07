package com.hqc.beck.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hqc.beck.model.Editors;

@Repository
public interface IEditorsRepository extends JpaRepository<Editors, Integer> {
    // @Query(name = "editors.searchByTyping")
    // List<Editors> searchByTyping(@Param("id") Integer id,
    //         @Param("name") String name,
    //         @Param("website") String website);

    Optional<Editors> findByNameAndWebsite(String name, String website);

    Optional<Editors> findByNameAndIdNot(String name, Integer id);

    Optional<Editors> findByWebsiteAndIdNot(String website, Integer id);
}// interface
