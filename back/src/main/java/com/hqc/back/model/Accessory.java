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
@Table(name = "accessory")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "compatible_with", nullable = true)
    private String compatibleWith;

    @Column(name = "color", nullable = true)
    private String color;

    @Column(name = "wireless", nullable = true)
    private Boolean wireless;

    @Column(name = "battery_life", nullable = true)
    private Integer batteryLife;

    @Column(name = "extra_features", columnDefinition = "TEXT", nullable = true)
    private String extraFeatures;

    @Column(name = "original_or_thirdparty", nullable = true)
    private String originalOrThirdparty;

}// class
