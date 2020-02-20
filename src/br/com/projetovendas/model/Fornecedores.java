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
public class Fornecedores extends Clientes{
    
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }    

    @Override
    public String toString() {
        String s = this.getId() + "-" + this.getNome();
        return s; 
    }
    
    
}
