package com.hqc.beck.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hqc.beck.model.Reviews;

@Repository
public interface IReviewsRepository extends JpaRepository<Reviews, Integer> {
    List<Reviews> findByOrderId(Integer orderId);

    List<Reviews> findByProductId(Integer productId);

    Optional<Reviews> findByOrderIdAndProductId(Integer orderId, Integer productId);

}// interface
