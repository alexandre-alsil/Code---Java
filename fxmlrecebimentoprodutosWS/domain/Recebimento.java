/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlrecebimentoprodutos.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alexandre
 */
public class Recebimento {
    
    private Integer codRecebimanto;
    
    private LocalDate dataRecebimento;
    
    private String notaFiscal;
    
    private double totalRecebimento;
    
    private List<Fornecedor> recebFornecedor;
    
    private List<Pedido> recebPedido;
    
    private List<ProdutoPedido> rececProdutoPedido;
    
    private List<ProdutoRecebimento> rececProdutoRecebimento;
    
    private Pedido pedido;

    public Integer getCodRecebimanto() {
        return codRecebimanto;
    }

    public void setCodRecebimanto(Integer codRecebimanto) {
        this.codRecebimanto = codRecebimanto;
    }

    public LocalDate getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(LocalDate dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public List<Fornecedor> getRecebFornecedor() {
        return recebFornecedor;
    }

    public void setRecebFornecedor(List<Fornecedor> recebFornecedor) {
        this.recebFornecedor = recebFornecedor;
    }

    public List<Pedido> getRecebPedido() {
        return recebPedido;
    }

    public void setRecebPedido(List<Pedido> recebPedido) {
        this.recebPedido = recebPedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public double getTotalRecebimento() {
        return totalRecebimento;
    }

    public void setTotalRecebimento(double totalRecebimento) {
        this.totalRecebimento = totalRecebimento;
    }

    public List<ProdutoPedido> getRececProdutoPedido() {
        return rececProdutoPedido;
    }

    public void setRececProdutoPedido(List<ProdutoPedido> rececProdutoPedido) {
        this.rececProdutoPedido = rececProdutoPedido;
    }

    public List<ProdutoRecebimento> getRececProdutoRecebimento() {
        return rececProdutoRecebimento;
    }

    public void setRececProdutoRecebimento(List<ProdutoRecebimento> rececProdutoRecebimento) {
        this.rececProdutoRecebimento = rececProdutoRecebimento;
    }
    
    
}
