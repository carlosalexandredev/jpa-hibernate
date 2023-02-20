package com.algaworks.curso.jpa2.controller;

import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.service.CadastroAcessorioService;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class CadastroAcessorioBean implements Serializable {

    private Acessorio acessorio;

    @Inject
    private CadastroAcessorioService cadastroAcessorioService;

    public CadastroAcessorioBean() {
        this.limpar();
    }

    public void salvar() {
        try {
            this.cadastroAcessorioService.salvar(acessorio);
            FacesUtil.addSuccessMessage("Acessório salvo com sucesso!");
        } catch (NegocioException e) {
            FacesUtil.addErrorMessage(e.getMessage());
        }

        this.limpar();
    }

    public void limpar() {
        this.acessorio = new Acessorio();
    }

    public Acessorio getAcessorio() {
        return acessorio;
    }
    public void setAcessorio(Acessorio acessorio) {
        this.acessorio = acessorio;
    }
}