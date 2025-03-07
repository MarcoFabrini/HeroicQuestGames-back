package com.hqc.beck.request;

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
public class DetailsOrderRequest {
    private Integer Id;
    private Integer quantity;
    private Double priceAtTime;
    private Integer ordersId;
    private Integer productId;

}// class
