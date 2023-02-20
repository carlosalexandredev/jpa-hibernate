package com.algaworks.curso.jpa2.modelo;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "ALG")
public class Aluguel {
    @Id
    @Column(name = "ID_ALG")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "VALOR_ALG")
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name="ID_CAR")
    private Carro carro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_APSG")
    private ApoliceSeguro apoliceSeguro;

}
