/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlrecebimentoprodutos.controller;

import fxmlrecebimentoprodutos.domain.Produto;
import fxmlrecebimentoprodutos.model.dao.ProdutoDAO;
import fxmlrecebimentoprodutos.model.database.Database;
import fxmlrecebimentoprodutos.model.database.DatabaseFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;






/**
 * FXML Controller class
 *
 * @author Alexandre
 */
public class FXMLProdutoConsultarController implements Initializable {

    @FXML
    private TableView<Produto> tableViewProdutos;
    
    @FXML
    private TableColumn<Produto, String> tableColReferencia;
    
    @FXML
    private TableColumn<Produto, Integer> tableColNomeProd;
    
    @FXML
    private Label labelCodProduto;
    
    @FXML
    private Label labelNomeProduto;
    
    @FXML
    private Label labelQtdeEstoque;
    
    @FXML
    private Label labelPrecoProduto;
    
    @FXML
    private Button btConsultar;
    
    @FXML
    private Button btAlterar;
    
    @FXML
    private Button btCancelar;
    
    @FXML
    private Button btRemover;
    
    
    private List<Produto> listProdutos;
    private ObservableList<Produto> observableListProdutos;
    
    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produtoDAO.setConnection(connection);
        carregarTableViewProduto();
        
        tableViewProdutos.getSelectionModel().selectedItemProperty().addListener((
                observable, oldValue, newValue) -> selecionarItemTableViewProduto(newValue));
        
        
    }    
    
    public void carregarTableViewProduto(){
        tableColReferencia.setCellValueFactory(new PropertyValueFactory<>("codProduto"));
        tableColNomeProd.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        
        listProdutos = produtoDAO.listar();
        
        observableListProdutos = FXCollections.observableArrayList(listProdutos);
        tableViewProdutos.setItems(observableListProdutos);
    }
    public void selecionarItemTableViewProduto(Produto produto){
        if(produto!=null){
            labelCodProduto.setText(produto.getCodProduto());
            labelNomeProduto.setText(produto.getNomeProduto());
            labelQtdeEstoque.setText(String.valueOf(produto.getQtdeEstoque()));
            labelPrecoProduto.setText(String.valueOf(produto.getPrecoProduto()));
         
        }else{
            labelCodProduto.setText("");
            labelNomeProduto.setText("");
            labelQtdeEstoque.setText("");
            labelPrecoProduto.setText("");

        }
    }
    @FXML
    public void handleButtonAlterar() throws IOException{
        Produto produto = tableViewProdutos.getSelectionModel().getSelectedItem();
        if(produto != null){
            boolean buttonConfirmarClicked = showFXMLAlterarProduto(produto);
            if(buttonConfirmarClicked){
                produtoDAO.alterar(produto);
                carregarTableViewProduto();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, escolha um produto na tabela!");
                alert.show();
            }
        }
    }
    
    public boolean showFXMLAlterarProduto(Produto produto)throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLProdutoInserirController.class.getResource("/fxmlrecebimentoprodutos/view/FXMLProdutoInserir.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registro de Produtos");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        FXMLProdutoInserirController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setProduto(produto);
        btAlterar.setVisible(true);
        
         dialogStage.showAndWait();
         
         return controller.isButtonConfirmarClicked();
    }
    
     @FXML
    public void handleButtonRemover() throws IOException{
        Produto produto = tableViewProdutos.getSelectionModel().getSelectedItem();
        if(produto != null){
                produto.setDisponivel(false);
                produtoDAO.remover(produto);
                carregarTableViewProduto();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, escolha um produto na tabela!");
                alert.show();
            }
        }
    }

