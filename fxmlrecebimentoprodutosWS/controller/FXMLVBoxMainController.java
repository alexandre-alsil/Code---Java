/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlrecebimentoprodutos.controller;

import fxmlrecebimentoprodutos.domain.Patrocinador;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Client;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

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
    @FXML
    private Alert alertPatrocinador;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            handleCarregarPatrocinadores();
        } catch (IOException ex) {
            Logger.getLogger(FXMLVBoxMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @FXML
    public void handleCarregarPatrocinadores() throws IOException {

        System.out.println("Conectando...");
        javax.ws.rs.client.Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/TrabalhoWebService3/webresources/trabalho/Patrocinador/list");

        String patrocinios = target.request().get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        
        
        TypeReference<ArrayList<Patrocinador>> mapType = new TypeReference<ArrayList<Patrocinador>>() {};
        ArrayList<Patrocinador> lista = new ArrayList<>();
        try {
            lista = mapper.readValue(patrocinios, mapType);
        } catch (IOException ex) {
            Logger.getLogger(FXMLVBoxMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String aux = "";
        for(Patrocinador patrocinador: lista){
            aux += patrocinador.getNomePatrocinador()+"\n";
        }
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(aux);
        alert.show();
        
        

        


    }

}
