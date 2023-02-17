package org.example.curso.agenda;

import lombok.extern.slf4j.Slf4j;
import org.example.curso.modelo.Agenda;
import org.example.curso.modelo.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
public class AtualizandoPrimeiroObjeto {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRUDPersistence");
        EntityManager em = emf.createEntityManager();
        Long id = 4L;

        Agenda agenda = em.find(Agenda.class, id);

        if(Objects.nonNull(agenda)) {
            em.getTransaction().begin();
            agenda = atualizaDados(agenda);
            em.getTransaction().commit();

            log.info(String.format("Agenda atualizada com sucesso: %s", agenda.toString()));
        }else {
            throw new NoSuchElementException(String.format("A agenda com o ID %s não foi encontrado", id));
        }
    }

    private static Agenda atualizaDados(Agenda agenda) {
        agenda.setNome("Gustavo");
        agenda.setTelefone("6421547178");
        return agenda;
    }
}
