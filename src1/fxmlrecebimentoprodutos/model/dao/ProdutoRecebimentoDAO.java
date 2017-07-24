/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlrecebimentoprodutos.model.dao;

import fxmlrecebimentoprodutos.domain.Produto;
import fxmlrecebimentoprodutos.domain.ProdutoRecebimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexandre
 */
public class ProdutoRecebimentoDAO {
    private Connection connection;
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
     public boolean inserir(ProdutoRecebimento produtoRecebimento) {
        String sql = "INSERT INTO produto_recebimento(cod_produto_recebimento_produto, cod_produto_recebimento_recebimento,qtde_recebimento) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, produtoRecebimento.getCodProdutoRecebimentoProduto().getIdProduto());
            stmt.setInt(2, produtoRecebimento.getCodRecebimento().getCodRecebimanto());
            stmt.setDouble(3, produtoRecebimento.getQtdeRecebimento());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
