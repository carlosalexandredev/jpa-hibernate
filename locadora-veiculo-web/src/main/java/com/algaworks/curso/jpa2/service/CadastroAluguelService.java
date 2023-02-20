package com.algaworks.curso.jpa2.service;

import com.algaworks.curso.jpa2.dao.AluguelDAO;
import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.Objects;

public class CadastroAluguelService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AluguelDAO aluguelDAO;

    @Transactional
    public void salvar(Aluguel aluguel) throws NegocioException {

        if (Objects.isNull(aluguel.getCarro())) {
            throw new NegocioException("O carro é obrigatório");
        }

        this.aluguelDAO.salvar(aluguel);
    }

}