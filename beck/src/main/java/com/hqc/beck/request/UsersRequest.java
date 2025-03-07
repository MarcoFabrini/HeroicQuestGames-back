package com.hqc.beck.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hqc.beck.dto.CartsDTO;
import com.hqc.beck.dto.DetailsShippingDTO;
import com.hqc.beck.dto.OrdersDTO;
import com.hqc.beck.dto.PayCardsDTO;
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
