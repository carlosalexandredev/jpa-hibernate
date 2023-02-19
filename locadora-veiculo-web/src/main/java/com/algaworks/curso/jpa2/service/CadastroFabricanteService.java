package com.algaworks.curso.jpa2.service;

import java.io.Serializable;
import java.util.Objects;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.util.jpa.Transactional;
import org.apache.commons.lang3.StringUtils;

public class CadastroFabricanteService implements Serializable {

	@Inject
	private FabricanteDAO fabricanteDAO;
	
	@Transactional
	public void salvar(Fabricante fabricante) throws NegocioException {
		if (StringUtils.isBlank(fabricante.getNome())) {
			throw new NegocioException("O nome do fabricante é obrigatório");
		}
		
		this.fabricanteDAO.salvar(fabricante);
	}
	
}
