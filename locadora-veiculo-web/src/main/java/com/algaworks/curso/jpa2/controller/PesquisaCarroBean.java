package com.algaworks.curso.jpa2.controller;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
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
public class PesquisaCarroBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Carro> carros;

    private Carro carroSelecionado;

    @Inject
    CarroDAO carroDAO;

    public List<Carro> getCarros() {
        return carros;
    }

    @PostConstruct
    public void inicializar() {
        this.carros = this.carroDAO.buscarTodos();
    }

    public void excluir() {
        try {
            carroDAO.excluir(carroSelecionado);
            this.carros.remove(carroSelecionado);
            FacesUtil.addSuccessMessage("Carro " + carroSelecionado.getModelo() + " excluído com sucesso.");
        } catch (NegocioException e) {
            FacesUtil.addErrorMessage(e.getMessage());
        }
    }

    public Carro getCarroSelecionado() {
        return carroSelecionado;
    }
    public void setCarroSelecionado(Carro carroSelecionado) {
        this.carroSelecionado = carroSelecionado;
    }

}