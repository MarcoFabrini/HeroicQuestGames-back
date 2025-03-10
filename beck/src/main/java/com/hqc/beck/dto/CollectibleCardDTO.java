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
public class CollectibleCardDTO {
    private String cardSet;
    private String rarity;
    private String edition;
    private String language;
    private String condition;
    private Boolean holographic;
    private Boolean graded;
    private String gradeAuthority;
    private Integer gradeScore;
}// class