package com.algaworks.curso.jpa2.dao;

import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;

public class AbstractDAO<CHAVE_PRIMARIA extends Serializable, ENTIDADE> {


    private EntityManager manager;
    private final Class<ENTIDADE> entidade;
    public AbstractDAO(Class<ENTIDADE> entidade) {
        this.entidade = entidade;
    }

    public ENTIDADE buscarPeloCodigo(CHAVE_PRIMARIA codigo) {
        return manager.find(this.entidade, codigo);
    }

    public void salvar(ENTIDADE entidade) {
        manager.merge(entidade);
    }

    public List<ENTIDADE> buscarTodos() {
        final String BUSCA_TODOS = String.format("FROM %s", entidade.getName());
        return manager.createQuery(BUSCA_TODOS).getResultList();
    }

    @Transactional
    public void excluir(CHAVE_PRIMARIA codigo) throws NegocioException {
        ENTIDADE entidade = buscarPeloCodigo(codigo);
        try {
            manager.remove(codigo);
            manager.flush();
        }catch (PersistenceException e){
            throw new NegocioException("Recurso não pode ser excluído.");
        }
    }
}
