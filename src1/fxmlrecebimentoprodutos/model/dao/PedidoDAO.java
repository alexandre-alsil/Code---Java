/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlrecebimentoprodutos.model.dao;

import fxmlrecebimentoprodutos.domain.Fornecedor;
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
public class PedidoDAO {
    private Connection connection;
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
public List<Pedido> listar() {
        String sql = "SELECT * FROM pedido";
        List<Pedido> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Pedido pedido = new Pedido();
                pedido.setCodPedido(resultado.getInt("id_pedido"));
                pedido.setDataPedido(resultado.getDate("data_pedido"));
                pedido.setFornecedorPedido(resultado.getString("cod_pedido_fornecedor"));
                pedido.setTotalPedido(resultado.getDouble("total_pedido"));
                
                retorno.add(pedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    
    public Pedido buscar(Pedido pedido) {
        String sql = "SELECT * FROM pedido WHERE id_pedido=?";
        Pedido retorno = new Pedido();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pedido.getCodPedido());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno.setCodPedido(resultado.getInt("id_pedido"));
                retorno.setTotalPedido(resultado.getDouble("total_pedido"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public boolean remover(Pedido pedido) {
        String sql = "DELETE FROM pedido WHERE id_pedido=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pedido.getCodPedido());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
   
}