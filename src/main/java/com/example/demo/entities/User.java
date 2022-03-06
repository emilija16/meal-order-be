package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.SQLDelete;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE user SET is_active = false WHERE id = ?")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(name = "firstName")
	@NotBlank
	public String firstName;

	@Column(name = "lastName")
	@NotBlank
	public String lastName;

	@Column(name = "username")
	@NotBlank
	public String username;

	@Column(name = "password", nullable = false)
	@NotBlank
	public String password;

	@Column(name = "email", unique = true)
	@NotBlank
	public String email;

	@Column(name = "phoneNumber")
	@NotBlank
	public String phoneNumber;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	public Role role;

	@Column(name = "is_active")
	private boolean isActive = true;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JsonBackReference
	private Set<Order> orders = new HashSet<>();
	
}
