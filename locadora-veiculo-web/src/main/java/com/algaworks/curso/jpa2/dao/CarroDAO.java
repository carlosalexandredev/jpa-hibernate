package com.algaworks.curso.jpa2.dao;

import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;

public class CarroDAO implements Serializable {
    @Inject
    private EntityManager manager;

    public Carro buscarPeloCodigo(Long codigo) {
        return manager.find(Carro.class, codigo);
    }

    public void salvar(Carro fabricante) {
        manager.merge(fabricante);
    }

    @SuppressWarnings("unchecked")
    public List<Carro> buscarTodos() {
        return manager.createQuery("from Carro").getResultList();
    }

    @Transactional
    public void excluir(Carro carro) throws NegocioException {
        carro = buscarPeloCodigo(carro.getCodigo());
        try {
            manager.remove(carro);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Carro não pode ser excluído.");
        }
    }

    @SuppressWarnings("JpaQueryApiInspection")
    public Carro buscarCarroComAcessorios(Long codigo) {
        return (Carro) manager.createQuery("select c from Carro c JOIN c.acessorios a where c.codigo = ?")
                .setParameter(1, codigo)
                .getSingleResult();
    }

}
