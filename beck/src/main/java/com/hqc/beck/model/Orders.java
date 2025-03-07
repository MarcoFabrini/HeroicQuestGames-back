package com.hqc.beck.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_ammount")
    private Double totalAmmount;

    @Column(name = "order_status")
    private String orderStatus;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "id_users", nullable = true)
    @JsonIgnore
    private Users user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DetailsOrder> listDetailsOrder;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reviews> listReviews;

    @ManyToOne
    @JoinColumn(name = "id_pay_cards", referencedColumnName = "id")
    @JsonIgnore
    private PayCards payCard;

}// class
