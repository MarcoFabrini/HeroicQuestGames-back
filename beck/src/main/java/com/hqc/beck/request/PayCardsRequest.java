package com.hqc.beck.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class PayCardsRequest {
    private Integer id;
    private String cardNumber;
    private String cardHolderName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yy")
    private Date expirationDate;
    private String cvv;
    private Integer userId;
    private String billingAddress;

}// class
