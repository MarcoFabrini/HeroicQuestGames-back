package com.hqc.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hqc.back.model.Orders;
import com.hqc.back.model.Users;

@Repository
public interface IOrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByUserId(Integer userId);

    List<Orders> findByUser(Users user);

    // @Query(name = "orders.searchByTyping")
    // List<Orders> searchByTyping(
    // @Param("id") Integer id,
    // @Param("payCard") Integer payCard,
    // @Param("user") Integer user);
}
