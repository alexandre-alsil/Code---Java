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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author victo
 */
public class FXMLGraficoQuantidadeProdutosEstFullController implements Initializable {

    @FXML
    private BarChart<String, Double> barChart;
    @FXML
    private CategoryAxis categoryAxis;
    @FXML
    private NumberAxis numberAxis;

    private ObservableList<String> observableListProduto = FXCollections.observableArrayList();

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private List<Produto> listProduto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produtoDAO.setConnection(connection);
        try{
        listProduto = produtoDAO.listarPorEstoque();
        List<String> listProdEst = new ArrayList();
        for(Produto produto : listProduto){
            listProdEst.add(produto.getNomeProduto());
        }
        
        observableListProduto.addAll(listProdEst);

        categoryAxis.setCategories(observableListProduto);
        
        Map<String, ArrayList> prod = produtoDAO.listarPorEstoqueMap();

        for (Map.Entry<String, ArrayList> prodItem : prod.entrySet()) {
            XYChart.Series<String, Double> series = new XYChart.Series<>();
            series.setName(prodItem.getKey());

            for (int i = 0; i < prodItem.getValue().size(); i = i + 2) {
                String nome;
                Double quantidade;

                nome = (String) prodItem.getValue().get(i);
                quantidade = (Double) prodItem.getValue().get(i + 1);

                series.getData().add(new XYChart.Data<>(nome, quantidade));
            }
            barChart.getData().add(series);
        }
        }catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText(ex.toString());
            alert.show();
        }
    }

}

