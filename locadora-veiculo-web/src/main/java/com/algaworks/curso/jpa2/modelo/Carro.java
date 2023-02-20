package com.algaworks.curso.jpa2.modelo;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "CAR")
public class Carro {
    @Id
    @Column(name = "ID_CAR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(name = "PLACA_CAR")
    private String placa;
    @Column(name = "CHASSI_CAR")
    private String chassi;
    @Column(name = "COR_CAR")
    private String cor;
    @Column(name = "DIARIA_CAR")
    private BigDecimal valorDiaria;

    @ManyToMany
    @JoinColumn(name = "ACR_CAR")
    private List<Acessorio> acessorios;

    @ManyToOne
    @JoinColumn(name = "ID_MCAR")
    private ModeloCarro modelo;
}
