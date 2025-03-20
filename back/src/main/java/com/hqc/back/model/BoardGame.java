package com.hqc.back.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "board_game")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "min_game_time", nullable = true)
    private Integer minGameTime;

    @Column(name = "max_game_time", nullable = true)
    private Integer maxGameTime;

    @Column(name = "min_player_number", nullable = true)
    private Integer minPlayerNumber;

    @Column(name = "max_player_number", nullable = true)
    private Integer maxPlayerNumber;

    @Column(name = "min_age", nullable = true)
    private Integer minAge;

}// class