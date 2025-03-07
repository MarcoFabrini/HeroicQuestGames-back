package com.hqc.beck.dto;

import java.util.Date;
import java.util.List;

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
public class ProductDTO {
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
    private EditorsDTO editorDTO;
    private List<AuthorsDTO> listAuthorsDTO;
    private List<CategoriesDTO> listCategoryDTO;
    private List<ReviewsDTO> listReviewsDTO;

    public ProductDTO(Integer id, String name, Double price, List<CategoriesDTO> listCategoryDTO) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.listCategoryDTO = listCategoryDTO;
    }

}// class
