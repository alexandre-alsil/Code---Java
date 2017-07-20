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
public class FornecedorDAO {
    private Connection connection;
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
     public List<Fornecedor> listarRelatorio() {
        String sql = "SELECT COUNT(p.id_pedido) as quantidade, f.id_fornecedor, f.nome_fornecedor, SUM(p.total_pedido) as soma FROM pedido p, fornecedor f WHERE f.id_fornecedor = p.cod_pedido_fornecedor GROUP BY f.id_fornecedor ORDER BY f.id_fornecedor";
        List<Fornecedor> retorno = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Fornecedor fornecedor = new Fornecedor();
               
                fornecedor.setNomeFornecedor(resultado.getString("nome_fornecedor"));
                fornecedor.setQtdePedidos(resultado.getInt("quantidade"));
                fornecedor.setTotalPedidos(resultado.getDouble("soma"));
                
                
                retorno.add(fornecedor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
