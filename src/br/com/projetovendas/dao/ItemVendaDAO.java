/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetovendas.dao;

import br.com.projetovendas.jdbc.ConnectionFactory;
import br.com.projetovendas.model.ItemVenda;
import br.com.projetovendas.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ederc
 */
public class ItemVendaDAO {

    private final Connection con;

    public ItemVendaDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //metodo cadasrta item
    public void cadastraItem(ItemVenda obj) {

        try {

            String sql = "insert into tb_itensvendas(venda_id,produto_id,qtd,subtotal)values(?,?,?,?)";

//Segundo Passo conectar o banco de dados e organizar o comando.
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProduto().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());

            //Terceiro passo - executar o comando sql
            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar! Motivo: " + erro);
        }

    }

    public List<ItemVenda> listarVendasHistorico(int venda_id) {

        try {
            //1 passo criar lista
            List<ItemVenda> lista = new ArrayList<>();
            //2 passo criar comando sql, organizar e executar.
            String sql = "select p.descricao, i.qtd, p.preco, i.subtotal from tb_itensvendas as i "
                    + " inner join tb_produtos as p on(i.produto_id = p.id) where i.venda_id = ? ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, venda_id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ItemVenda obj = new ItemVenda();
                Produtos prod = new Produtos();

                obj.setQtd(rs.getInt("i.qtd"));
                prod.setDescricao(rs.getString("p.descricao"));
                prod.setPreco(rs.getDouble("p.preco"));
                obj.setSubtotal(rs.getDouble("i.subtotal"));

                obj.setProduto(prod);

                lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

}
