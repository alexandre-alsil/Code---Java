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
public class ProdutoRecebimento {
    
    private Integer idProdutoRecebimento;
    
    private Produto codProdutoRecebimentoProduto;
    
    private Recebimento codRecebimento;
    
    private int qtdeRecebimento;
    
    private List<Produto> itemProdutoRecebimento;

    public Integer getIdProdutoRecebimento() {
        return idProdutoRecebimento;
    }

    public void setIdProdutoRecebimento(Integer idProdutoRecebimento) {
        this.idProdutoRecebimento = idProdutoRecebimento;
    }

    public Produto getCodProdutoRecebimentoProduto() {
        return codProdutoRecebimentoProduto;
    }

    public void setCodProdutoRecebimentoProduto(Produto codProdutoRecebimentoProduto) {
        this.codProdutoRecebimentoProduto = codProdutoRecebimentoProduto;
    }

    public Recebimento getCodRecebimento() {
        return codRecebimento;
    }

    public void setCodRecebimento(Recebimento codRecebimento) {
        this.codRecebimento = codRecebimento;
    }

   

    public List<Produto> getItemProdutoRecebimento() {
        return itemProdutoRecebimento;
    }

    public void setItemProdutoRecebimento(List<Produto> itemProdutoRecebimento) {
        this.itemProdutoRecebimento = itemProdutoRecebimento;
    }

    public int getQtdeRecebimento() {
        return qtdeRecebimento;
    }

    public void setQtdeRecebimento(int qtdeRecebimento) {
        this.qtdeRecebimento = qtdeRecebimento;
    }
    
    
}
