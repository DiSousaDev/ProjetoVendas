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
public class Vendas {
    
    private int id;
    private Clientes cliente;
    private String data_venda;
    private double total_venda;
    private String obervacoes;

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
     * @return the cliente
     */
    public Clientes getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the data_venda
     */
    public String getData_venda() {
        return data_venda;
    }

    /**
     * @param data_venda the data_venda to set
     */
    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }

    /**
     * @return the total_venda
     */
    public double getTotal_venda() {
        return total_venda;
    }

    /**
     * @param total_venda the total_venda to set
     */
    public void setTotal_venda(double total_venda) {
        this.total_venda = total_venda;
    }

    /**
     * @return the obervacoes
     */
    public String getObervacoes() {
        return obervacoes;
    }

    /**
     * @param obervacoes the obervacoes to set
     */
    public void setObervacoes(String obervacoes) {
        this.obervacoes = obervacoes;
    }
    
}
