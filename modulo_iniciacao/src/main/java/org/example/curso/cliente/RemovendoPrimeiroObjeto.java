package org.example.curso.cliente;

import lombok.extern.slf4j.Slf4j;
import org.example.curso.modelo.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
public class RemovendoPrimeiroObjeto {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRUDPersistence");
        EntityManager em = emf.createEntityManager();
        Long id = 5L;

        Cliente cliente = em.find(Cliente.class, id);

        if(Objects.nonNull(cliente)) {
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();

            log.info(String.format("Cliente removido com sucesso: %s", cliente.toString()));
        } else {
            throw new NoSuchElementException(String.format("O Cliente com o ID %s não foi encontrado", id));
        }

    }
}
