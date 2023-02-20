package com.algaworks.curso.jpa2.controller;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.modelo.ApoliceSeguro;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.service.CadastroAluguelService;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class NovoAluguelBean implements Serializable {
    private Aluguel aluguel;

    private List<Carro> carros;

    @Inject
    private CadastroAluguelService cadastroAluguelService;

    @Inject
    private CarroDAO carroDAO;

    public void salvar() {
        try {
            this.cadastroAluguelService.salvar(aluguel);
            FacesUtil.addSuccessMessage("Aluguel salvo com sucesso!");
        } catch (NegocioException e) {
            FacesUtil.addErrorMessage(e.getMessage());
        }

        this.limpar();
    }

    @PostConstruct
    public void inicializar() {
        this.limpar();

        this.carros = this.carroDAO.buscarTodos();
    }

    public void limpar() {
        this.aluguel = new Aluguel();
        this.aluguel.setApoliceSeguro(new ApoliceSeguro());
    }

    public Aluguel getAluguel() {
        return aluguel;
    }
    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }

    public List<Carro> getCarros() {
        return carros;
    }

}
