/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetovendas.model;

/**
 *
 * @author ederc
 */
public class Produtos {
    
    private int id;
    private String cod_original;
    private String descricao;
    private double preco;
    private int qtd_estoque;
    private Fornecedores fornecedor;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public String getCod_original() {
        return cod_original;
    }

    public void setCod_original(String cod_original) {
        this.cod_original = cod_original;
    }
   
    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * @return the qtd_estoque
     */
    public int getQtd_estoque() {
        return qtd_estoque;
    }

    /**
     * @param qtd_estoque the qtd_estoque to set
     */
    public void setQtd_estoque(int qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    /**
     * @return the fornecedor
     */
    public Fornecedores getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(Fornecedores fornecedor) {
        this.fornecedor = fornecedor;
    }
       
}
