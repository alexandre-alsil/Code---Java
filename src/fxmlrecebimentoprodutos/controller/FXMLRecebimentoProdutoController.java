/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlrecebimentoprodutos.controller;

import fxmlrecebimentoprodutos.domain.Pedido;
import fxmlrecebimentoprodutos.domain.Produto;
import fxmlrecebimentoprodutos.domain.ProdutoPedido;
import fxmlrecebimentoprodutos.domain.ProdutoRecebimento;
import fxmlrecebimentoprodutos.domain.Recebimento;
import fxmlrecebimentoprodutos.model.dao.PedidoDAO;
import fxmlrecebimentoprodutos.model.dao.ProdutoDAO;
import fxmlrecebimentoprodutos.model.dao.ProdutoPedidoDAO;
import fxmlrecebimentoprodutos.model.dao.ProdutoRecebimentoDAO;
import fxmlrecebimentoprodutos.model.dao.RecebimentoDAO;
import fxmlrecebimentoprodutos.model.database.Database;
import fxmlrecebimentoprodutos.model.database.DatabaseFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alexandre
 */
public class FXMLRecebimentoProdutoController implements Initializable {

    @FXML
    private TextField tfNotaFiscal;
    @FXML
    private DatePicker dpDataRecebimento;
    @FXML
    private Label labelFornecedor;
    @FXML
    private TableView tvItemPedido;
    @FXML 
    
    
    private TableColumn<ProdutoRecebimento, Integer> tcCodProduto;
    @FXML
    private TableColumn<ProdutoRecebimento, String> tcNomeProduto;
    @FXML
    private TableColumn<ProdutoRecebimento, Double> tcQtdeProduto;
    
    @FXML
    private Button btConfirmar;
    @FXML
    private Button btCancelar;
    
    private List<Pedido> listPedido;
    private List<ProdutoPedido> listProdutoPedido;
    private List<ProdutoRecebimento> listProdutoRecebimento;
    
    private ObservableList<Pedido> observableListPedido;
    private ObservableList<ProdutoPedido> observableListProdutoPedido;
    
    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final ProdutoPedidoDAO produtoPedidoDAO = new ProdutoPedidoDAO();
    private final ProdutoRecebimentoDAO produtoRecebimentoDAO = new ProdutoRecebimentoDAO();
    private final RecebimentoDAO recebimentoDAO = new RecebimentoDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Pedido pedido;
    private Recebimento recebimento;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
        System.out.println("pedido3"+pedido);
        System.out.println("pedido4"+pedido.getListProdutoPedido());
        listProdutoPedido = produtoPedidoDAO.listarPorPedido(pedido);
        observableListProdutoPedido = FXCollections.observableArrayList(listProdutoPedido);
        tvItemPedido.setItems(observableListProdutoPedido);
        System.out.println("pedido5" +observableListProdutoPedido);
    }

    
    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    public Recebimento getRecebimento() {
        return recebimento;
    }

    public void setRecebimento(Recebimento recebimento) {
        this.recebimento = recebimento;

    }

    
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pedidoDAO.setConnection(connection);
        produtoPedidoDAO.setConnection(connection);
        produtoRecebimentoDAO.setConnection(connection);
        recebimentoDAO.setConnection(connection);
        produtoDAO.setConnection(connection);
        
        tcCodProduto.setCellValueFactory(new PropertyValueFactory<>("idProdutoPedido"));
        tcNomeProduto.setCellValueFactory(new PropertyValueFactory<>("codProdutoPedidoProduto"));
        tcQtdeProduto.setCellValueFactory(new PropertyValueFactory<>("qtdePedido"));
        
        
        //selecionarItemTableViewPedido();

    }    
    
    @FXML
    public void handleButtonReceber() throws SQLException{
        
         connection.setAutoCommit(false);
                recebimentoDAO.setConnection(connection);
                //produtoPedidoDAO.setConnection(connection);
                produtoRecebimentoDAO.setConnection(connection);
               // produtoDAO.setConnection(connection);
               Recebimento recebimento = new Recebimento();
               recebimento.setPedido(pedido);
               recebimento.setNotaFiscal(tfNotaFiscal.getText());
               recebimento.setDataRecebimento(dpDataRecebimento.getValue());
               
               ArrayList<ProdutoRecebimento> listProdutoRecebimento = new ArrayList<>();
               
               recebimento.setRececProdutoRecebimento(listProdutoRecebimento);
               
               recebimentoDAO.inserir(recebimento);
               
               
                for(ProdutoPedido listProdutoPedido: observableListProdutoPedido){
                    Produto produto = listProdutoPedido.getCodProdutoPedidoProduto();
                    ProdutoRecebimento produtoRecebimento = new ProdutoRecebimento();
                    produtoRecebimento.setCodRecebimento(recebimentoDAO.buscarUltimoRecebimento());
                    produtoRecebimento.setCodProdutoRecebimentoProduto(produto);
                    int r = produtoRecebimento.getCodRecebimento().getCodRecebimanto();
                    recebimento.setCodRecebimanto(r);
                    int i = listProdutoPedido.getQtdePedido();
                    produtoRecebimento.setQtdeRecebimento(i);
                    double v = pedido.getTotalPedido();
                    System.out.println(pedido.getTotalPedido());
                    recebimento.setTotalRecebimento(pedido.getTotalPedido() + v);
                    listProdutoRecebimento.add(produtoRecebimento);
                    produtoRecebimentoDAO.inserir(produtoRecebimento);
                    produtoPedidoDAO.remover(listProdutoPedido);
                    produto.setQtdeEstoque(produto.getQtdeEstoque() + i);
                    produto.setDisponivel(true);
                    produtoDAO.alterar(produto);
                    recebimentoDAO.alterar(recebimento);
                    //pedidoDAO.remover(pedido);
                    connection.commit();
                    
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Dados alterados com sucesso!");
                    alert.show();
                    
                    
                    
                    //listProdutoPedido.setCodProdutoPedidoPedido(pedido);
                }
        
        buttonConfirmarClicked = true;
        dialogStage.close();
    }

}
