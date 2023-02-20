package com.algaworks.curso.jpa2.service;

import com.algaworks.curso.jpa2.dao.ModeloCarroDAO;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.util.jpa.Transactional;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.Objects;

public class CadastroModeloCarroService implements Serializable {

	@Inject
	private ModeloCarroDAO modeloCarroDAO;
	
	@Transactional
	public void salvar(ModeloCarro modeloCarro) throws NegocioException {
		if (StringUtils.isBlank(modeloCarro.getDescricao())) {
			throw new NegocioException("A descrição do modelo do carro é obrigatóri!a");
		}
		if(Objects.isNull(modeloCarro.getFabricante())){
			throw new NegocioException("O fabricante é obrigatório!");
		}
		this.modeloCarroDAO.salvar(modeloCarro);
	}

}
