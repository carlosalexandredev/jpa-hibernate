package org.example.curso.agenda;

import lombok.extern.slf4j.Slf4j;
import org.example.curso.modelo.Agenda;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

@Slf4j
public class SalvandoPrimeiroObjeto {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRUDPersistence");
        EntityManager em = emf.createEntityManager();

        Agenda agenda = criarAgenda();

        em.getTransaction().begin();
        em.persist(agenda);
        em.getTransaction().commit();

        log.info(String.format("Agenda salva com sucesso: %s", agenda.toString()));
    }

    private static Agenda criarAgenda() {
        return new Agenda(null, "Lucas Santiago", "9630815459", new Date());
    }
}
