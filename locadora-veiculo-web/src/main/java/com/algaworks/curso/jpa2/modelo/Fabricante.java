package com.algaworks.curso.jpa2.modelo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "FBC")
public class Fabricante {
	@Id
	@Column(name = "ID_FBC")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;
	@Column(name = "NAME_FBC")
	private String nome;
	
}
