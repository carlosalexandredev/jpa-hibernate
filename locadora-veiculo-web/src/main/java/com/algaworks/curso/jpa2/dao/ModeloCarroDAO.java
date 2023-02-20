package com.algaworks.curso.jpa2.dao;

import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
public class ModeloCarroDAO implements Serializable {

	private static final String BUSCA_TODOS = "FROM ModeloCarro";
	@Inject
	private EntityManager em;

	public void salvar(ModeloCarro modeloCarro) {
		em.merge(modeloCarro);
	}

    public List<ModeloCarro> buscarTodos() {
		return em.createQuery(BUSCA_TODOS).getResultList();
    }

	@Transactional
	public void excluir(ModeloCarro modeloSelecionado) throws NegocioException {
		modeloSelecionado = em.find(ModeloCarro.class, modeloSelecionado.getCodigo());
		em.remove(modeloSelecionado);
		em.flush();
	}

	public ModeloCarro buscarPeloCodigo(Long codigo) {
		ModeloCarro modeloCarro = em.find(ModeloCarro.class, codigo);

		if(Objects.nonNull(modeloCarro)) {
			log.info(String.format("Agenda consultada com sucesso: %s", modeloCarro.toString()));
			return modeloCarro;
		}else {
			throw new NoSuchElementException(String.format("A agenda com o ID %s não foi encontrada", codigo));
		}
	}
}
