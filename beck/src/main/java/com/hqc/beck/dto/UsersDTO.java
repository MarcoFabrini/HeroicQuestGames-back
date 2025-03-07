package com.hqc.beck.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hqc.beck.utils.Roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersDTO {
	private Integer id;
	private String fullName;
	private String email;
	private Roles role;
	private List<OrdersDTO> listOrdersDTO;
	private CartsDTO cartDTO;
	private List<PayCardsDTO> listPayCardsDTO;
	private List<DetailsShippingDTO> detailsShippingDTO;
	private Boolean active;

}// class
