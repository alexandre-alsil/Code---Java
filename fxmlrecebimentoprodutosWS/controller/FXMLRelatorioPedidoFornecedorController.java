/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlrecebimentoprodutos.controller;

import fxmlrecebimentoprodutos.domain.Fornecedor;
import fxmlrecebimentoprodutos.model.dao.FornecedorDAO;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Alexandre
 */
public class FXMLRelatorioPedidoFornecedorController implements Initializable {

    @FXML
    private TableView<Fornecedor> tableViewFornecedor;
    @FXML
    private TableColumn<Fornecedor, String> tableColunmForn;
    @FXML
    private TableColumn<Fornecedor, Integer> tableColunmQtdePed;
    @FXML
    private TableColumn<Fornecedor, Double> tableColunmTotalPed;
    @FXML
    private Button buttonImprimir;
    /**
     * Initializes the controller class.
     */
  
    private List<Fornecedor> listFornecedor;
    private ObservableList<Fornecedor> observableListFornecedor;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    private final FornecedorDAO fornecedorDAO = new FornecedorDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fornecedorDAO.setConnection(connection);
        
        carregarTableViewFornecedor();
    }    
    
    public void carregarTableViewFornecedor() {
        

        tableColunmForn.setCellValueFactory(new PropertyValueFactory<>("nomeFornecedor"));
        tableColunmQtdePed.setCellValueFactory(new PropertyValueFactory<>("qtdePedidos"));
        tableColunmTotalPed.setCellValueFactory(new PropertyValueFactory<>("totalPedidos"));

        listFornecedor = fornecedorDAO.listarRelatorio();

        observableListFornecedor = FXCollections.observableArrayList(listFornecedor);
        tableViewFornecedor.setItems(observableListFornecedor);
    }
    
    public void handleImprimir() throws JRException{
        URL url = getClass().getResource("/fxmlrecebimentoprodutos/relatorios/JAVAFXMVCRelatorioPedidoFornecedorjrxml.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);//null: caso não existam filtros
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);//false: não deixa fechar a aplicação principal
        jasperViewer.setVisible(true);
    }
   
    
}
