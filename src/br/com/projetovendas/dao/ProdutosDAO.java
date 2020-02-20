/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetovendas.dao;

import br.com.projetovendas.jdbc.ConnectionFactory;
import br.com.projetovendas.model.Fornecedores;
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
public class ProdutosDAO {

    private final Connection con;

    public ProdutosDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrar(Produtos obj) {
        //primeiro passo criar comando sql
        try {
            String sql = "insert into tb_produtos(cod_original,descricao,preco,qtd_estoque, for_id)"
                    + "values(?,?,?,?,?)";
            //Segundo Passo conectar o banco de dados e organizar o comando.
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getCod_original());
            stmt.setString(2, obj.getDescricao());
            stmt.setDouble(3, obj.getPreco());
            stmt.setInt(4, obj.getQtd_estoque());
            stmt.setInt(5, obj.getFornecedor().getId());

            //Terceiro passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar! Motivo: " + erro);
        }
    }

    public List<Produtos> listarProdutos() {

        try {
            //1 passo criar lista
            List<Produtos> lista = new ArrayList<>();
            //2 passo criar comando sql, organizar e executar.
            String sql = "select p.id, p.cod_original, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id)";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setCod_original(rs.getString("p.cod_original"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);

                lista.add(obj);

            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public void alterar(Produtos obj) {
        //primeiro passo criar comando sql
        try {
            String sql = "update tb_produtos set cod_original=?, descricao=?, preco=?, qtd_estoque=?, for_id=? where id=?";
            //Segundo Passo conectar o banco de dados e organizar o comando.
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getCod_original());
            stmt.setString(2, obj.getDescricao());
            stmt.setDouble(3, obj.getPreco());
            stmt.setInt(4, obj.getQtd_estoque());
            stmt.setInt(5, obj.getFornecedor().getId());
            stmt.setInt(6, obj.getId());

            //Terceiro passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar! Motivo: " + erro);
        }
    }

    public void excluir(Produtos obj) {
        //primeiro passo criar comando sql
        try {
            String sql = "delete from tb_produtos where id = ?";

            //Segundo Passo conectar o banco de dados e organizar o comando.
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            //Terceiro passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Registro excluido com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir! Motivo: " + erro);
        }
    }

    public List<Produtos> listarProdutosPorNome(String nome) {

        try {
            //1 passo criar lista
            List<Produtos> lista = new ArrayList<>();
            //2 passo criar comando sql, organizar e executar.
            String sql = "select p.id, p.cod_original, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setCod_original(rs.getString("p.cod_original"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);

                lista.add(obj);

            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public Produtos consultaProdutosPorNome(String nome) {

        try {

            String sql = "select p.id, p.cod_original, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {

                obj.setId(rs.getInt("p.id"));
                obj.setCod_original(rs.getString("p.cod_original"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);
            }
            return obj;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public Produtos consultaProdutosPorId(int id) {

        try {

            String sql = "select p.id, p.cod_original, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                    + "inner join tb_fornecedores as f on (p.for_id = f.id) where p.id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {

                obj.setId(rs.getInt("p.id"));
                obj.setCod_original(rs.getString("p.cod_original"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);
            }
            return obj;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    //metodo baixar estoque

    public void baixaEstoque(int id, int qtd_nova) {

        try {
            String sql = "update tb_produtos set qtd_estoque=? where id=?";
            //Segundo Passo conectar o banco de dados e organizar o comando.
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);

            //Terceiro passo - executar o comando sql
            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro baixar estoque! " + erro);
        }
    }

    public void adicionarEstoque(int id, int qtd_nova) {

        try {
            String sql = "update tb_produtos set qtd_estoque=? where id=?";
            //Segundo Passo conectar o banco de dados e organizar o comando.
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);

            //Terceiro passo - executar o comando sql
            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro baixar estoque! " + erro);
        }
    }
    
    public int retornaUltimaVenda(int id) {

        try {
            int qtd_estoque = 0;
            String sql = "select qtd_estoque from tb_produtos where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                qtd_estoque = (rs.getInt("qtd_estoque"));
            }
            return qtd_estoque;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
