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
public class AccessoryDTO {
    private String compatibleWith;
    private String color;
    private Boolean wireless;
    private Integer batteryLife;
    private String extraFeatures;
    private String originalOrThirdParty;
}// class 
