/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlrecebimentoprodutos.domain;

import java.util.List;

/**
 *
 * @author Alexandre
 */
public class ProdutoPedido {
    
    private int idProdutoPedido;
    
    private Produto codProdutoPedidoProduto;
    
    private Pedido codProdutoPedidoPedido;
    
    private int qtdePedido;
    
    

    public int getIdProdutoPedido() {
        return idProdutoPedido;
    }

    public void setIdProdutoPedido(int idProdutoPedido) {
        this.idProdutoPedido = idProdutoPedido;
    }

    public Produto getCodProdutoPedidoProduto() {
        return codProdutoPedidoProduto;
    }

    public void setCodProdutoPedidoProduto(Produto codProdutoPedidoProduto) {
        this.codProdutoPedidoProduto = codProdutoPedidoProduto;
    }

    public Pedido getCodProdutoPedidoPedido() {
        return codProdutoPedidoPedido;
    }

    public void setCodProdutoPedidoPedido(Pedido codProdutoPedidoPedido) {
        this.codProdutoPedidoPedido = codProdutoPedidoPedido;
    }

    

    public int getQtdePedido() {
        return qtdePedido;
    }

    public void setQtdePedido(int qtdePedido) {
        this.qtdePedido = qtdePedido;
    }

    
    
    
}
