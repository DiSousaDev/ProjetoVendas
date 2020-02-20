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
public class ItemVenda {
    
    private int id;
    private Vendas venda;
    private Produtos produto;
    private int qtd;
    private double subtotal;

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

    /**
     * @return the venda
     */
    public Vendas getVenda() {
        return venda;
    }

    /**
     * @param venda the venda to set
     */
    public void setVenda(Vendas venda) {
        this.venda = venda;
    }

    /**
     * @return the produto
     */
    public Produtos getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    /**
     * @return the qtd
     */
    public int getQtd() {
        return qtd;
    }

    /**
     * @param qtd the qtd to set
     */
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    /**
     * @return the subtotal
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
}
