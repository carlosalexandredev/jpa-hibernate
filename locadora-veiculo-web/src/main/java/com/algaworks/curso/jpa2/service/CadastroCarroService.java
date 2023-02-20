package com.algaworks.curso.jpa2.service;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.util.jpa.Transactional;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.io.Serializable;

public class CadastroCarroService implements Serializable {
    @Inject
    private CarroDAO carroDAO;

    @Transactional
    public void salvar(Carro carro) throws NegocioException {

        if (StringUtils.isBlank(carro.getPlaca())) {
            throw new NegocioException("A placa é obrigatória");
        }

        this.carroDAO.salvar(carro);
    }

}
