package org.example.curso.conta;

import lombok.extern.slf4j.Slf4j;
import org.example.curso.modelo.Conta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Scanner;

@Slf4j
public class Transferencia {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static Scanner entrada = new Scanner(System.in);;
    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("CRUDPersistence");
        em = emf.createEntityManager();

        Conta conta1 = em.find(Conta.class, 1L);
        Conta conta2 = em.find(Conta.class, 2L);

        realizaPersistenciaConta(conta1, conta2);
    }

    private static void realizaPersistenciaConta(Conta conta1, Conta conta2) {
        System.out.println("Conta 01:");
        conta1 = verificaConta(conta1);

        System.out.println("Conta 02:");
        conta2 = verificaConta(conta2);

        em.getTransaction().begin();
        em.persist(conta1);
        em.persist(conta2);
        em.getTransaction().commit();
        log.info("Contas salvas com sucesso");

        em.close();

        realizaTransferencia(conta1, conta2);

    }

    private static Conta verificaConta(Conta conta1) {
        if (Objects.isNull(conta1)) {
            conta1 = criarConta();
            log.info(String.format("Saldo conta: R$%s", conta1.getSaldo()));
        }
        return conta1;
    }

    private static void realizaTransferencia(Conta conta1, Conta conta2) {
        em = emf.createEntityManager();

        System.out.println("Digite o valor que sera transferido da Conta 01 para Conta 02: ");
        BigDecimal transferencia = entrada.nextBigDecimal();

        em.getTransaction().begin();
        conta1.setSaldo(conta1.getSaldo().subtract(transferencia));
        conta2.setSaldo(conta2.getSaldo().add(transferencia));

        em.merge(conta1);
        em.merge(conta2);

        if((conta1.getSaldo().compareTo(BigDecimal.ZERO)) == 0){
            em.getTransaction().commit();
            log.info("Transfêrencia realizada com sucesso");
        }else {
            em.getTransaction().rollback();

            conta1.setSaldo(conta1.getSaldo().add(transferencia));
            conta2.setSaldo(conta2.getSaldo().subtract(transferencia));
            transferencia = BigDecimal.ZERO;
            log.info("Saldo insuficiente para realizar a transfêrencia");
        }

        exibeExtrato(conta1, conta2, transferencia);
    }

    private static Conta criarConta() {
        System.out.println("Digite o saldo inicial da conta:");
        BigDecimal saldoInicialConta1 = entrada.nextBigDecimal();
        return new Conta(null, saldoInicialConta1);
    }

    private static void exibeExtrato(Conta conta1, Conta conta2, BigDecimal transferencia) {
        log.info(String.format(
                """
                \n
                     --- EXTRATO ---
                Valor transferido: R$%s   
                Saldo Conta 01:    R$%s
                Saldo Conta 02:    R$%s 
                """, transferencia, conta1.getSaldo(), conta2.getSaldo()));
    }
}
