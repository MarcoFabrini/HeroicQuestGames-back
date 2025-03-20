package com.hqc.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hqc.back.model.PayCards;

@Repository
public interface IPayCardsRepository extends JpaRepository<PayCards, Integer> {

}// interface
