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
public class ReviewsDTO {
    private Integer id;
    private Integer score;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date createdAt;
    private ProductDTO productDTO;
    private OrdersDTO orderDTO;
    private Boolean active;

    public ReviewsDTO(Integer id, Integer score, String description, Date createdAt, Boolean active) {
        this.id = id;
        this.score = score;
        this.description = description;
        this.createdAt = createdAt;
        this.active = active;
    }

}// class
