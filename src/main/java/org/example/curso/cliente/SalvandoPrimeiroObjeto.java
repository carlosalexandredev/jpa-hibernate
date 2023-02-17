package org.example.curso.cliente;

import lombok.extern.slf4j.Slf4j;
import org.example.curso.modelo.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Slf4j
public class SalvandoPrimeiroObjeto {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRUDPersistence");
        EntityManager em = emf.createEntityManager();

        Cliente cliente = criarCliente();

        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();

        log.info("Cliente Salvo com sucesso");
    }

    private static Cliente criarCliente() {
        return new Cliente(null, "Carlos", 22, "M", "Desenvolvedor Java");
    }
}
