package com.algaworks.curso.jpa2.modelo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MCAR")
public class ModeloCarro {

    @Id
    @Column(name = "ID_MCAR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(name = "DESCRICAO_MCAR")
    private String descricao;
    @ManyToOne
    private Fabricante fabricante;
}
