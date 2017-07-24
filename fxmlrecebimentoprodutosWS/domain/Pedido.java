/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlrecebimentoprodutos.domain;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Alexandre
 */
public class Pedido {
    
    private int codPedido;
    
    private Date dataPedido;
    
    private String fornecedorPedido;
    
    private Double totalPedido;
    
    private List<ProdutoPedido> listProdutoPedido;
    
    private Fornecedor fornecedor;

   
    public Pedido(){
        
    }
    
    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getFornecedorPedido() {
        return fornecedorPedido;
    }

    public void setFornecedorPedido(String fornecedorPedido) {
        this.fornecedorPedido = fornecedorPedido;
    }

    public Double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(Double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public List<ProdutoPedido> getListProdutoPedido() {
        return listProdutoPedido;
    }

    public void setListProdutoPedido(List<ProdutoPedido> listProdutoPedido) {
        this.listProdutoPedido = listProdutoPedido;
    }
    @Override
    public String toString(){
        return this.codPedido+"";
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    
    }




    
    
    

