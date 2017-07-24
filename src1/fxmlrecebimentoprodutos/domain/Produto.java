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
public class Produto {
    
    private int idProduto;
    
    private String codProduto;
    
    private String nomeProduto;
    
    private Double qtdeEstoque;
    
    private Double precoProduto;
    
    private boolean disponivel;

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(Double qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    
    
    
    @Override
    public String toString(){
        return this.nomeProduto;
    }
            
    
}
