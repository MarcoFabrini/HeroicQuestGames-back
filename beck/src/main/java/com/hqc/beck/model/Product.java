package com.hqc.beck.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "pubblication_date")
    private Date date;

    @Column(name = "min_game_time")
    private Integer minGameTime;

    @Column(name = "max_game_time")
    private Integer maxGameTime;

    @Column(name = "min_player_number")
    private Integer minPlayerNumber;

    @Column(name = "max_player_number")
    private Integer maxPlayerNumber;

    @Column(name = "min_age")
    private Integer minAge;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "authors_product", joinColumns = @JoinColumn(name = "id_product"), inverseJoinColumns = @JoinColumn(name = "id_authors"))
    @JsonIgnore
    private List<Authors> listAuthors;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_product", joinColumns = @JoinColumn(name = "id_product"), inverseJoinColumns = @JoinColumn(name = "id_category"))
    @JsonIgnore
    private List<Categories> listCategory;

    @ManyToOne
    @JoinColumn(name = "id_editors", nullable = true)
    @JsonIgnore
    private Editors editor;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DetailsCart> listDetailsCarts;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reviews> listReviews;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DetailsOrder> listDetailsOrder;

}// class
