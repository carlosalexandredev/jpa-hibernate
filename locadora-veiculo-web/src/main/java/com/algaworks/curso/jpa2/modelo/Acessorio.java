package com.algaworks.curso.jpa2.modelo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ACR")
public class Acessorio {
    @Id
    @Column(name = "ID_ACR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "DESCRICACAO_ARC")
    private String descricao;
}
