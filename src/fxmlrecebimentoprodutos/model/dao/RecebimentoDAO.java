/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlrecebimentoprodutos.model.dao;

import fxmlrecebimentoprodutos.domain.Produto;
import fxmlrecebimentoprodutos.domain.Recebimento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexandre
 */
public class RecebimentoDAO {
    private Connection connection;
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Recebimento recebimento) {
        String sql = "INSERT INTO recebimento(cod_pedido, data_recebimento,total_recebimento, nota_fiscal_recebimento) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, recebimento.getPedido().getCodPedido());
            stmt.setDate(2, Date.valueOf(recebimento.getDataRecebimento()));
            stmt.setDouble(3, recebimento.getTotalRecebimento());
            //stmt.setDouble(3, produto.getQtdeEstoque());
            stmt.setString(4, recebimento.getNotaFiscal());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Recebimento buscarUltimoRecebimento(){
        String sql = "SELECT max(id_recebimento) FROM recebimento";
        Recebimento retorno = new Recebimento();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            if(resultado.next()){
                retorno.setCodRecebimanto(resultado.getInt("max"));
                return retorno;
            }
        }catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
    }
   return retorno;
}
    
    public boolean alterar(Recebimento recebimento) {
        String sql = "UPDATE recebimento SET total_recebimento=? WHERE id_recebimento=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, recebimento.getTotalRecebimento());
            stmt.setInt(2, recebimento.getCodRecebimanto());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}