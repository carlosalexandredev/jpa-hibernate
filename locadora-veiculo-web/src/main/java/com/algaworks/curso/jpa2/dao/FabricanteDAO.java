package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FabricanteDAO implements Serializable {

	private static final String BUSCA_TODOS = "FROM Fabricante";
	@Inject
	private EntityManager em;
	
	public void salvar(Fabricante fabricante) {
		em.merge(fabricante);
	}

    public List<Fabricante> buscarTodos() {
		return em.createQuery(BUSCA_TODOS).getResultList();
    }

	@Transactional
	public void excluir(Fabricante fabricanteSelecionado) throws NegocioException {
		fabricanteSelecionado = em.find(Fabricante.class, fabricanteSelecionado.getCodigo());
		em.remove(fabricanteSelecionado);
		em.flush();
	}

	public Fabricante buscarPeloCodigo(Long codigo) {
		Fabricante fabricante = em.find(Fabricante.class, codigo);

		if(Objects.nonNull(fabricante)) {
			log.info(String.format("Agenda consultada com sucesso: %s", fabricante.toString()));
			return fabricante;
		}else {
			throw new NoSuchElementException(String.format("A agenda com o ID %s não foi encontrada", codigo));
		}
	}
}
