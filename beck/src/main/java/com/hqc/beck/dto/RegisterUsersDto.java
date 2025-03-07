package com.hqc.beck.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hqc.beck.utils.Roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterUsersDto {
	private String email;
	private String password;
	private String fullName;
	private Roles role;

}// class
