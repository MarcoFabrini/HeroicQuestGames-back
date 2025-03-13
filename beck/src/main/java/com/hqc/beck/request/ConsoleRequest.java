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
public class ConsoleRequest extends ProductRequest {
    private Integer consoleId;
    private String brand;
    private String model;
    private Integer storageCapacity;
    private String condition;
    private Integer warrantyMonths;
    private String includedAccessories;
}// class
