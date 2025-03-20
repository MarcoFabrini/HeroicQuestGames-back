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
@Table(name = "console")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Console {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "brand", nullable = true)
    private String brand;

    @Column(name = "model", nullable = true)
    private String model;

    @Column(name = "storage_capacity", nullable = true)
    private Integer storageCapacity;

    @Column(name = "condition_console", nullable = true)
    private String condition;

    @Column(name = "warranty_months", nullable = true)
    private Integer warrantyMonths;

    @Column(name = "included_accessories", columnDefinition = "TEXT", nullable = true)
    private String includedAccessories;

}// class
