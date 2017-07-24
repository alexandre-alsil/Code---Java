/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlrecebimentoprodutos.model.dao;

import fxmlrecebimentoprodutos.domain.Pedido;
import fxmlrecebimentoprodutos.domain.Produto;
import fxmlrecebimentoprodutos.domain.ProdutoPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexandre
 */
public class ProdutoPedidoDAO {
    private Connection connection;
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
public List<ProdutoPedido> listarPorPedido(Pedido pedido) {
        System.out.println("cod pedido denntro da dao produtopedido"+pedido.getCodPedido());
        String sql = "SELECT * FROM produto_pedido WHERE cod_produto_pedido_pedido=?";
        List<ProdutoPedido> retorno = new ArrayList<>();
        //ProdutoDAO produtoDAO = new ProdutoDAO();
        //produtoDAO.setConnection(connection);
        
        
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pedido.getCodPedido());
            ResultSet resultado = stmt.executeQuery();
            System.out.println("cod produto dentro do listar pedido produto pedido dao"+resultado);
            while (resultado.next()) {
                ProdutoPedido produtoPedido = new ProdutoPedido();
                Pedido pedido1 = new Pedido();
                Produto produto = new Produto();
                ProdutoDAO produtoDAO = new ProdutoDAO();
                PedidoDAO pedidoDAO = new PedidoDAO();
                pedidoDAO.setConnection(connection);
                produtoDAO.setConnection(connection);
                //produto = produtoDAO.buscar(produto);
              
                //produtoPedido.setIdProdutoPedido(resultado.getInt("cod_produto_pedido"));
                produtoPedido.setIdProdutoPedido(resultado.getInt("id_produto_pedido"));
                produtoPedido.setQtdePedido(resultado.getInt("qtde_pedido"));
                
                produto.setIdProduto(resultado.getInt("cod_produto_pedido_produto"));
                
                pedido1.setCodPedido(resultado.getInt("cod_produto_pedido_pedido"));
                produtoPedido.setCodProdutoPedidoProduto(produtoDAO.buscar(produto));
                System.out.println("nome produto: "+produtoPedido.getCodProdutoPedidoProduto().getNomeProduto());
                produtoPedido.setCodProdutoPedidoPedido(pedidoDAO.buscar(pedido1));
                
                
                
               //produtoPedido.setCodProdutoPedidoProduto(produto);
               //produtoPedido.setCodProdutoPedidoPedido(pedido1);
                
                
                retorno.add(produtoPedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
public boolean remover(ProdutoPedido produtoPedido) {
        String sql = "DELETE FROM produto_pedido WHERE id_produto_pedido=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, produtoPedido.getIdProdutoPedido());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}