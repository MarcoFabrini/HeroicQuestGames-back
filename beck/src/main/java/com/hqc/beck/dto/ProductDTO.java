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
    private Integer productId;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date date;
    private String description;
    private Integer stockQuantity;
    private Double price;
    private String imageUrl;
    private Boolean active;

    private EditorsDTO editorDTO;
    private List<AuthorsDTO> listAuthorsDTO;
    private List<CategoriesDTO> listCategoryDTO;
    private List<ReviewsDTO> listReviewsDTO;

    private BoardGameDTO boardGameDTO;
    private ConsoleDTO consoleDTO;
    private CollectibleCardDTO collectibleCardDTO;
    private AccessoryDTO accessoryDTO;

    public ProductDTO(Integer productId, String name, Double price, String imageUrl, BoardGameDTO boardGameDTO, ConsoleDTO consoleDTO,
            CollectibleCardDTO collectibleCardDTO, AccessoryDTO accessoryDTO) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.boardGameDTO = boardGameDTO;
        this.consoleDTO = consoleDTO;
        this.collectibleCardDTO = collectibleCardDTO;
        this.accessoryDTO = accessoryDTO;
    }

}// class
