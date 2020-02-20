/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetovendas.dao;

import br.com.projetovendas.jdbc.ConnectionFactory;
import br.com.projetovendas.model.Clientes;
import br.com.projetovendas.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ederc
 */
public class VendasDAO {

    private final Connection con;

    public VendasDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //cadastrar venda
    public void cadastrarVenda(Vendas obj) {
        //primeiro passo criar comando sql
        try {
            String sql = "insert into tb_vendas(cliente_id,data_venda,total_venda,observacoes)values(?,?,?,?)";
            try ( //Segundo Passo conectar o banco de dados e organizar o comando.
                    PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, obj.getCliente().getId());
                stmt.setString(2, obj.getData_venda());
                stmt.setDouble(3, obj.getTotal_venda());
                stmt.setString(4, obj.getObervacoes());

                //Terceiro passo - executar o comando sql
                stmt.execute();
                stmt.close();
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar venda! " + erro);
        }
    }

    //retorna a ultima venda
    public int retornaUltimaVenda() {
        try {
            int idvenda = 0;
            String sql = "select max(id) id from tb_vendas";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Vendas p = new Vendas();
                p.setId(rs.getInt("id"));

                idvenda = p.getId();

            }
            return idvenda;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<Vendas> listarVendasPorPeriodo(LocalDate data_inicial, LocalDate data_final) {

        try {
            //1 passo criar lista
            List<Vendas> lista = new ArrayList<>();
            //2 passo criar comando sql, organizar e executar.
            String sql = "select v.id, date_format(v.data_venda, '%d/%m/%Y') as data_formatada, c.nome, v.total_venda, v.observacoes from tb_vendas as v "
                    + "inner join tb_clientes as c on (v.cliente_id = c.id) where v.data_venda BETWEEN ? AND ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicial.toString());
            stmt.setString(2, data_final.toString());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vendas obj = new Vendas();
                Clientes c = new Clientes();

                obj.setId(rs.getInt("v.id"));
                obj.setData_venda(rs.getString("data_formatada"));
                c.setNome(rs.getString("c.nome"));
                obj.setTotal_venda(rs.getDouble("v.total_venda"));
                obj.setObervacoes(rs.getString("v.observacoes"));

                obj.setCliente(c);

                lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public double listarTotalVendas(LocalDate data) {

        try {
            double total_venda = 0;

            String sql = "select sum(total_venda) as total from tb_vendas where data_venda = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data.toString());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total_venda = rs.getDouble("total");
            }

            return total_venda;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
        return (0);
    }
}
