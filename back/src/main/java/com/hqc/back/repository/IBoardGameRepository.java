package com.hqc.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hqc.back.model.BoardGame;

public interface IBoardGameRepository extends JpaRepository<BoardGame, Integer>{

}// interface
