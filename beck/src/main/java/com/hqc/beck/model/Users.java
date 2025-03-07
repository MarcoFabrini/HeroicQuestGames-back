package com.hqc.beck.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hqc.beck.utils.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "users")
@Entity
public class Users implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer id;

	@Column(nullable = false)
	private String fullName;

	@Column(unique = true, length = 100, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(name = "active", nullable = false)
	private Boolean active;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Roles roles;

	@CreationTimestamp
	@Column(updatable = false, name = "created_at")
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<DetailsShipping> listDetailsShippings;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<PayCards> listPayCards;

	@OneToOne(mappedBy = "user")
	@JsonIgnore
	private Carts cart;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Orders> listOrders;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(roles); // Ritorna il ruolo come GrantedAuthority
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}// class
