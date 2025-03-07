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
public class ProductRequest {
    private Integer id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date date;
    private Integer minGameTime;
    private Integer maxGameTime;
    private Integer minPlayerNumber;
    private Integer maxPlayerNumber;
    private Integer minAge;
    private String description;
    private Integer stockQuantity;
    private Double price;
    private Boolean active;
    private Integer editorsId;
    private Integer authorsId;
    private Integer categoryId;
    private Integer reviewsId;

}// class
