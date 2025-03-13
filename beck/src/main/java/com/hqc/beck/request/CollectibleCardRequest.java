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
public class CollectibleCardRequest extends ProductRequest {
    private Integer collectibleCardId;
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
