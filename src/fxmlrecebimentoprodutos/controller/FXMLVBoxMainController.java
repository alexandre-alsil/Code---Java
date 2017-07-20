/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlrecebimentoprodutos.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Alexandre
 */
public class FXMLVBoxMainController implements Initializable {

    @FXML
    private MenuItem menuItemListarPedido; 
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private MenuItem menuItemInserir;
    @FXML
    private MenuItem menuItemConsultar;
    @FXML
    private MenuItem graficoProd;
    @FXML
    private MenuItem menuItemRelatoriosQuantidadeProdutos;
    @FXML
    private MenuItem menuItemRelatorioPedido;
    
    
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    public void handleMenuItemCadastrosClientes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxmlrecebimentoprodutos/view/PedidoConsulta.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuCadastroProduto() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxmlrecebimentoprodutos/view/FXMLProdutoInserir.fxml"));
        anchorPane.getChildren().setAll(a);
        
        
    }
    
    @FXML
    public void handleMenuConsultarProduto() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxmlrecebimentoprodutos/view/FXMLProdutoConsultar.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuGraficoProduto() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxmlrecebimentoprodutos/view/FXMLGraficoQuantidadeProdutosEstFull.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemRelatoriosQuantidadeProdutos() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxmlrecebimentoprodutos/view/FXMLRelatorioProduto.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemRelatorio() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxmlrecebimentoprodutos/view/FXMLRelatorioPedidoFornecedor.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
   
}
