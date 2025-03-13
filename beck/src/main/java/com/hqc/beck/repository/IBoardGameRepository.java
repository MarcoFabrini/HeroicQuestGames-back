package com.hqc.beck.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hqc.beck.model.BoardGame;

public interface IBoardGameRepository extends JpaRepository<BoardGame, Integer>{

}// interface
