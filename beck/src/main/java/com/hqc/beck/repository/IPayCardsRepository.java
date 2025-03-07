package com.hqc.beck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hqc.beck.model.PayCards;

@Repository
public interface IPayCardsRepository extends JpaRepository<PayCards, Integer> {

}// interface
