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
@Table(name = "details_shipping")
public class DetailsShipping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "lastname", nullable = false)
	private String lastname;

	@Column(name = "country", nullable = false)
	private String country; // nazione

	@Column(name = "state_region", nullable = false)
	private String stateRegion; // regione o stato

	@Column(name = "cap", nullable = false)
	private String cap; // codice postale

	@Column(name = "city", nullable = false)
	private String city; // città

	@Column(name = "address", nullable = false)
	private String address; // indirizzo

	@Column(name = "active")
	private Boolean active;

	@ManyToOne
	@JoinColumn(name = "id_users", nullable = false)
	@JsonIgnore
	private Users user;

}// class
