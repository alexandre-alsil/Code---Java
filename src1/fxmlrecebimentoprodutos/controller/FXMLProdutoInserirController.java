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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alexandre
 */
public class FXMLProdutoInserirController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField tfCodProd;
    
    @FXML
    private TextField tfNomeProd;
    
    @FXML
    private TextField tfPrecoProd;
    
    @FXML
    private Button btConfirma;
    
    @FXML
    private Button btCancela;
    
    @FXML
    private Button btAltera;
    
    //private int tela;
    private Produto produto;
    private boolean buttonConfirmarClicked = false;
    private Stage dialogStage;
    
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    
    

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.tfCodProd.setText(produto.getCodProduto());
        this.tfNomeProd.setText(produto.getNomeProduto());
        this.tfPrecoProd.setText(String.valueOf(produto.getPrecoProduto()));
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produtoDAO.setConnection(connection);
        
        
        
    }    
    @FXML
    public void handleButtonInserir() throws IOException{
        Produto produto = new Produto();
        if(validarDados()){
            produto.setCodProduto(tfCodProd.getText());
            produto.setNomeProduto(tfNomeProd.getText());
            produto.setPrecoProduto(Double.parseDouble(tfPrecoProd.getText()));
            produto.setQtdeEstoque(0.0);
            produto.setDisponivel(true);
            produtoDAO.inserir(produto);
            
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Cadastro de Produto Realizado");
            alert.show();
            
            tfCodProd.setText("");
            tfNomeProd.setText("");
            tfPrecoProd.setText("");
            
             
        }
            
            
            
        }
    
     @FXML
     //teste
     public void visibilidadeBotao(int tela){
         System.out.println("teste tela"+tela);
         if(tela == 1){
            btAltera.setVisible(false);
            btConfirma.setVisible(true);
        }else{
            btAltera.setVisible(true);
            btConfirma.setVisible(false);
        }
     }
    
    @FXML
    public void handleButtonAlterar(){
        if(validarDados()){
        produto.setCodProduto(tfCodProd.getText());
        produto.setNomeProduto(tfNomeProd.getText());
        produto.setPrecoProduto(Double.parseDouble(tfPrecoProd.getText()));
        produto.setDisponivel(true);
        //produto.setQtdeEstoque(Double.parseDouble().getText("qtdeEstoque"));
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Alteração de Produto Realizado");
        alert.show();
        
        
        buttonConfirmarClicked = true;
        dialogStage.close();
    }
    }
    
    private boolean validarDados(){
        String errorMessage = "";
        if(tfCodProd.getText()==null || tfCodProd.getText().length()==0 || tfCodProd.getText().length()>30){
         errorMessage +="Codigo Produto Invalido ";    
        }
        
        if(tfNomeProd.getText()==null || tfNomeProd.getText().length()==0 || tfNomeProd.getText().length()>300){
         errorMessage +="Nome Produto Invalido ";    
        }
        
        if(tfPrecoProd.getText()==null || tfPrecoProd.getText().length()==0 || tfPrecoProd.getText().length()>10){
        errorMessage +="Preço Produto Invalido ";    
        }
        
        if (errorMessage.length() ==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no Cadastro de Produto");
            alert.setHeaderText("Os campos abaixo estão inválidos");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
                  
        }
        
    }
    
    }
 
