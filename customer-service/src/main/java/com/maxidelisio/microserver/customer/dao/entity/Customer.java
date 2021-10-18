package com.maxidelisio.microserver.customer.dao.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_customers")
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "El numero de documento no puede ser vacio")
	@Size(min = 8 , max = 8 , message= "Deben ser 8 numeros")
	@Column(name = "number_id", unique = true, length = 8 , nullable = false)
	private String numberID;
	
	@NotEmpty(message = "El nombre no debe ser vacio")
	@Column(name = "first_name",  nullable = false)
	private String firstName;
	
	@NotEmpty(message = "El apellido no debe ser vacio")
	@Column(name = "last_name",  nullable = false)
	private String lastName;
	
	@NotEmpty(message = "El email no debe ser vacio")
	@Column(name = "email",  nullable = false)
	@Email(message = "No es una direccion de correo bien formada")
	private String email;
	
	@Column(name="photo_url")
	private String photoUrl;
	
	@NotNull(message = "La región no puede ser vacia")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="region_id")
	@JsonIgnoreProperties("hibernateLazyInitializer")
	private Region region;
	
	private String state;

}
