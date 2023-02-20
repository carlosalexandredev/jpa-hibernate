package com.algaworks.curso.jpa2.service;

import com.algaworks.curso.jpa2.dao.AcessorioDAO;
import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.util.jpa.Transactional;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.io.Serializable;

public class CadastroAcessorioService implements Serializable {
    @Inject
    private AcessorioDAO acessorioDAO;

    @Transactional
    public void salvar(Acessorio acessorio) throws NegocioException {

        if (StringUtils.isBlank(acessorio.getDescricao())) {
            throw new NegocioException("A descrição do acessório é obrigatório");
        }

        this.acessorioDAO.salvar(acessorio);
    }

}
