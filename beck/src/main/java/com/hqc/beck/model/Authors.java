package com.hqc.beck.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "country")
    private String country;

    @Column(name = "biography", columnDefinition = "TEXT")
    private String biography;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @ManyToMany(mappedBy = "listAuthors", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> listProducts;

}// class
