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
public class BoardGameDTO {
    private Integer minGameTime;
    private Integer maxGameTime;
    private Integer minPlayerNumber;
    private Integer maxPlayerNumber;
    private Integer minAge;
}// class
