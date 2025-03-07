package com.hqc.beck.dto;

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
public class PayCardsDTO {
    private Integer id;
    private String cardNumber;
    private String cardHolderName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yy")
    private Date expirationDate;
    private Boolean active;

}// class
