package org.example.curso.cliente;

import org.example.curso.modelo.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ConsultaComJPQL {
    private static final String BUSCA_LISTA_MULHERES = "FROM Cliente WHERE sexo = 'F'";
    private static final String BUSCA_LISTA_MENORES_DE_IDADE = "FROM Cliente WHERE idade < 18";
    private static final String BUSCA_LISTA_CLIENTES_NOME_INICIAL_C = "FROM Cliente WHERE nome LIKE 'C%'";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRUDPersistence");
        EntityManager em = emf.createEntityManager();

        List<Cliente> clientesMulheres = consultaMulheres(em);
        List<Cliente> clienteMenoresDeIdade = consultaMenoresDeIdade(em);
        List<Cliente> clientesInicalC = consultaClienteInicialC(em);

        System.out.println("Busca mulheres:");
        clientesMulheres.stream().map(Cliente::toString).forEach(System.out::println);

        System.out.println("Busca menores de idade:");
        clienteMenoresDeIdade.stream().map(Cliente::toString).forEach(System.out::println);

        System.out.println("Busca clientes com inicial [C]:");
        clientesInicalC.stream().map(Cliente::toString).forEach(System.out::println);
    }

    private static List<Cliente> consultaClienteInicialC(EntityManager em) {
        return em.createQuery(BUSCA_LISTA_CLIENTES_NOME_INICIAL_C, Cliente.class).getResultList();
    }

    private static List<Cliente> consultaMenoresDeIdade(EntityManager em) {
        return em.createQuery(BUSCA_LISTA_MENORES_DE_IDADE, Cliente.class).getResultList();
    }

    private static List<Cliente> consultaMulheres(EntityManager em) {
        return em.createQuery(BUSCA_LISTA_MULHERES, Cliente.class).getResultList();
    }
}