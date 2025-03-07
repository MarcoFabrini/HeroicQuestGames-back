package com.hqc.beck.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "details_order")
public class DetailsOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price_at_time")
    private Double priceAtTime;

    @ManyToOne
    @JoinColumn(name = "id_orders")
    @JsonIgnore
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "id_product")
    @JsonIgnore
    private Product product;

}// class
