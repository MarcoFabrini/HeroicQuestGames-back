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
@Table(name = "collectible_card")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CollectibleCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "card_set", nullable = true)
    private String cardSet;

    @Column(name = "rarity", nullable = true)
    private String rarity;

    @Column(name = "edition", nullable = true)
    private String edition;

    @Column(name = "language", nullable = true)
    private String language;

    @Column(name = "condition_card", columnDefinition = "TEXT", nullable = true)
    private String condition;

    @Column(name = "holographic", nullable = true)
    private Boolean holographic;

    @Column(name = "graded", nullable = true)
    private Boolean graded;

    @Column(name = "grade_authority", nullable = true)
    private String gradeAuthority;

    @Column(name = "grade_score", nullable = true)
    private Integer gradeScore;

}// class
