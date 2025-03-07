package com.hqc.beck.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailsShippingDTO {
	private Integer id;
	private String name;
	private String lastname;
	private String country; // nazione
	private String stateRegion; // regione o stato
	private String cap; // codice postale
	private String city; // città
	private String address; // indirizzo
	private Boolean active;

}// class
