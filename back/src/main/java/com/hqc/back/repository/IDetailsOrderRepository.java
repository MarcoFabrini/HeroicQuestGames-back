package com.hqc.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hqc.back.model.DetailsOrder;
import com.hqc.back.model.Orders;

@Repository
public interface IDetailsOrderRepository extends JpaRepository<DetailsOrder, Integer> {

    List<DetailsOrder> findByOrder(Orders order);

    // @Query(name = "detailsOrder.findOrderForReview")
    // Long findOrderForReview(
    // @Param("userId") Integer userId,
    // @Param("gameId") Integer gameId);

}
