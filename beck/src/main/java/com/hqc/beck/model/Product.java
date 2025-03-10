package com.hqc.beck.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "pubblication_date", nullable = true)
    private Date date;

    @Column(name = "description", columnDefinition = "TEXT", nullable = true)
    private String description;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "active")
    private Boolean active;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_board_game", referencedColumnName = "id", nullable = true)
    private BoardGame boardGame;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_console", referencedColumnName = "id", nullable = true)
    private Console console;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_collectible_card", referencedColumnName = "id", nullable = true)
    private CollectibleCard collectibleCard;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_accessory", referencedColumnName = "id", nullable = true)
    private Accessory accessory;

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
