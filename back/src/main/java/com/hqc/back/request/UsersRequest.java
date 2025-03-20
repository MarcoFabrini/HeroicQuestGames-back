package com.hqc.back.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hqc.back.dto.CartsDTO;
import com.hqc.back.dto.DetailsShippingDTO;
import com.hqc.back.dto.OrdersDTO;
import com.hqc.back.dto.PayCardsDTO;
import com.hqc.back.utils.Roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersRequest {
	private Integer id;
	private String fullName;
	private String email;
	private Roles role;
	private List<OrdersDTO> listOrdersDTO;
	private CartsDTO carts;
	private List<PayCardsDTO> listPayCardsDTO;
	private List<DetailsShippingDTO> detailsShipping;
	private Boolean active;

}// class
