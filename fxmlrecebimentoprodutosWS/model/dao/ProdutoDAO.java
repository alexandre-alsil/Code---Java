/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlrecebimentoprodutos.model.dao;

import fxmlrecebimentoprodutos.domain.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexandre
 */
public class ProdutoDAO {
    private Connection connection;
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Produto produto) {
        String sql = "INSERT INTO produto(cod_produto, nome_produto,qtde_estoque, preco_unit, disp_produto) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getCodProduto());
            stmt.setString(2, produto.getNomeProduto());
            stmt.setDouble(3, produto.getQtdeEstoque());
            //stmt.setDouble(3, produto.getQtdeEstoque());
            stmt.setDouble(4, produto.getPrecoProduto());
            stmt.setBoolean(5, produto.isDisponivel());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Produto produto) {
        String sql = "UPDATE produto SET cod_produto=?, nome_produto=?, qtde_estoque=?, preco_unit=?, disp_produto=?  WHERE id_produto=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getCodProduto());
            stmt.setString(2, produto.getNomeProduto());
            stmt.setDouble(3, produto.getQtdeEstoque());
            stmt.setDouble(4, produto.getPrecoProduto());
            stmt.setBoolean(5, produto.isDisponivel());
            stmt.setInt(6, produto.getIdProduto());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean remover(Produto produto) {
        String sql = "UPDATE produto SET disp_produto=? WHERE id_produto=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setBoolean(1, produto.isDisponivel());
            stmt.setInt(2, produto.getIdProduto());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Produto> listar() {
        String sql = "SELECT * FROM produto WHERE disp_produto = true";
        List<Produto> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(resultado.getInt("id_produto"));
                produto.setCodProduto(resultado.getString("cod_produto"));
                produto.setNomeProduto(resultado.getString("nome_produto"));
                produto.setQtdeEstoque(resultado.getDouble("qtde_estoque"));
                produto.setPrecoProduto(resultado.getDouble("preco_unit"));
                produto.setDisponivel(resultado.getBoolean("disp_produto"));
                retorno.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Produto buscar(Produto produto) {
        String sql = "SELECT * FROM produto WHERE id_produto=?";
        Produto retorno = new Produto();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, produto.getIdProduto());
            System.out.println("cod produto: "+produto.getIdProduto());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno.setIdProduto(resultado.getInt("id_produto"));
                retorno.setCodProduto(resultado.getString("cod_produto"));
                retorno.setNomeProduto(resultado.getString("nome_produto"));
                retorno.setQtdeEstoque(resultado.getDouble("qtde_estoque"));
                retorno.setPrecoProduto(resultado.getDouble("preco_unit"));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Produto> listarPorEstoque() {
        String sql = "SELECT * FROM produto WHERE disp_produto = true;";
        List<Produto> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(resultado.getInt("id_produto"));
                produto.setCodProduto(resultado.getString("cod_produto"));
                produto.setNomeProduto(resultado.getString("nome_produto"));
                produto.setQtdeEstoque(resultado.getDouble("qtde_estoque"));
                produto.setPrecoProduto(resultado.getDouble("preco_unit"));
                retorno.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Map<String, ArrayList> listarPorEstoqueMap(){
      String sql = "SELECT nome_produto, qtde_estoque FROM produto WHERE disp_produto = true";
        Map<String, ArrayList> retorno = new HashMap();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = new Produto();
                ArrayList listProduto = new ArrayList();
                listProduto.add(resultado.getString("nome_produto"));
                listProduto.add(resultado.getDouble("qtde_estoque"));
                retorno.put(resultado.getString("nome_produto"), listProduto);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Produto> listarTodos() {
        String sql = "SELECT * FROM produto ";
        List<Produto> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(resultado.getInt("id_produto"));
                produto.setCodProduto(resultado.getString("cod_produto"));
                produto.setNomeProduto(resultado.getString("nome_produto"));
                produto.setQtdeEstoque(resultado.getDouble("qtde_estoque"));
                produto.setPrecoProduto(resultado.getDouble("preco_unit"));
                produto.setDisponivel(resultado.getBoolean("disp_produto"));
                retorno.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
