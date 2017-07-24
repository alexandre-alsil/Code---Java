/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlrecebimentoprodutos.domain;

/**
 *
 * @author Alexandre
 */
public class Fornecedor {
    
    private Integer codFornecedor;
    
    private String nomeFornecedor;
    
    private int qtdePedidos;
    
    private Double totalPedidos;
    
    

    public Integer getCodFornecedor() {
        return codFornecedor;
    }

    public void setCodFornecedor(Integer codFornecedor) {
        this.codFornecedor = codFornecedor;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public int getQtdePedidos() {
        return qtdePedidos;
    }

    public void setQtdePedidos(int qtdePedidos) {
        this.qtdePedidos = qtdePedidos;
    }

    public Double getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(Double totalPedidos) {
        this.totalPedidos = totalPedidos;
    }
    
    
    
}
