package com.hqc.beck.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hqc.beck.model.Carts;
import com.hqc.beck.model.Users;

@Repository
public interface ICartsRepository extends JpaRepository<Carts, Integer> {

    Optional<Carts> findByUser(Users user);

}
