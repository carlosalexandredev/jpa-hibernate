package com.algaworks.curso.jpa2.modelo;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "APSG")
public class ApoliceSeguro {
    @Id
    @Column(name = "ID_APSG")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(name = "VALOR_FRANQUIA_APSG")
    private BigDecimal valorFranquia;
    @Column(name = "TERCEIROS_APSG")
    private Boolean protecaoTerceiro;
    @Column(name = "CAUSAS_NATURAIS_APSG")
    private Boolean protecaoCausasNaturais;
    @Column(name = "ROUBO_APSG")
    private Boolean protecaoRoubo;
}
