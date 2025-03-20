package com.hqc.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hqc.back.model.Carts;
import com.hqc.back.model.Users;

@Repository
public interface ICartsRepository extends JpaRepository<Carts, Integer> {

    Optional<Carts> findByUser(Users user);

}
