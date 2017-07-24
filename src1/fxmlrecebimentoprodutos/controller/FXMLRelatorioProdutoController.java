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
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Alexandre
 */
public class FXMLRelatorioProdutoController implements Initializable {

    @FXML
    private TableView<Produto> tableViewProdutos;
    @FXML
    private TableColumn<Produto, String> tableColumnProdutoCodigo;
    @FXML
    private TableColumn<Produto, String> tableColumnProdutoNome;
    @FXML
    private TableColumn<Produto, Double> tableColumnProdutoPreco;
    @FXML
    private TableColumn<Produto, Double> tableColumnProdutoQuantidade;
    @FXML
    private TableColumn<Produto, Boolean> tableColumnProdutoDisp;
    @FXML
    private Button buttonImprimir;
    /**
     * Initializes the controller class.
     */
    private List<Produto> listProdutos;
    private ObservableList<Produto> observableListProdutos;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produtoDAO.setConnection (connection);
        
        carregarTableViewProdutos();
    }    
    
    public void carregarTableViewProdutos() {
        
        tableColumnProdutoCodigo.setCellValueFactory(new PropertyValueFactory<>("codProduto"));
        tableColumnProdutoNome.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        tableColumnProdutoPreco.setCellValueFactory(new PropertyValueFactory<>("precoProduto"));
        tableColumnProdutoQuantidade.setCellValueFactory(new PropertyValueFactory<>("qtdeEstoque"));
        tableColumnProdutoDisp.setCellValueFactory(new PropertyValueFactory<>("disponivel"));

        listProdutos = produtoDAO.listarTodos();

        observableListProdutos = FXCollections.observableArrayList(listProdutos);
        tableViewProdutos.setItems(observableListProdutos);
    }
    
    /*public void handleImprimir() throws JRException{
        URL url = getClass().getResource("/javafxmvc/relatorios/JAVAFXMVCRelatorioProdutos.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);//null: caso não existam filtros
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);//false: não deixa fechar a aplicação principal
        jasperViewer.setVisible(true);
    }*/

}  
    

