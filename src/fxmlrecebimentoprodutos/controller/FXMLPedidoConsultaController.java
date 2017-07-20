/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlrecebimentoprodutos.controller;

import fxmlrecebimentoprodutos.model.dao.PedidoDAO;
import fxmlrecebimentoprodutos.domain.Pedido;
import fxmlrecebimentoprodutos.domain.Produto;
import fxmlrecebimentoprodutos.domain.ProdutoPedido;
import fxmlrecebimentoprodutos.domain.ProdutoRecebimento;
import fxmlrecebimentoprodutos.domain.Recebimento;
import fxmlrecebimentoprodutos.model.dao.ProdutoDAO;
import fxmlrecebimentoprodutos.model.dao.ProdutoPedidoDAO;
import fxmlrecebimentoprodutos.model.dao.ProdutoRecebimentoDAO;
import fxmlrecebimentoprodutos.model.dao.RecebimentoDAO;
import fxmlrecebimentoprodutos.model.database.Database;
import fxmlrecebimentoprodutos.model.database.DatabaseFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
public class FXMLPedidoConsultaController implements Initializable {

    @FXML
    private TableView<Pedido> tableViewPedido;
    @FXML
    private TableColumn<Pedido, Date> tableColumnDataPe;
    @FXML
    private TableColumn<Pedido, String> tableColumnFornecedorPe;
    @FXML
    private TableColumn<Pedido, Double> tableColumnTotalPe;
    @FXML
    private TableColumn<Pedido, Integer> tableColumnCodPedido;
    /*@FXML
    private TableView<ProdutoPedido> tableViewProdutosPedidos;
    @FXML
    private TableColumn<ProdutoPedido, String> tableColumnCodProdPed;
    @FXML
    private TableColumn<Produto, Produto> tableColumnNomeProdPed;
    @FXML
    private TableColumn<ProdutoPedido, Double> tableColumnQtdProdPed;
     */

    @FXML
    private TableView<ProdutoPedido> tableViewProdutosPedidos;
    @FXML
    private TableColumn<ProdutoPedido, Integer> tableColumnCodProdPed;
    @FXML
    private TableColumn<ProdutoPedido, Produto> tableColumnNomeProdPed;
    @FXML
    private TableColumn<ProdutoPedido, Integer> tableColumnQtdProdPed;

    @FXML
    private Button buttonReceber;
    @FXML
    private Button buttonVoltar;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pedidoDAO.setConnection(connection);
        produtoPedidoDAO.setConnection(connection);
        carregarTableViewPedido();
        //selecionarItemTableViewPedido();
        tableViewPedido.getSelectionModel().selectedItemProperty().addListener((
                observable, oldValue, newValue) -> selecionarItemTableViewPedido(newValue));

        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        /*tableViewPedido.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewPedido(newValue));
         */
    }

    public void carregarTableViewPedido() {

        tableColumnDataPe.setCellValueFactory(new PropertyValueFactory<>("dataPedido"));
        tableColumnFornecedorPe.setCellValueFactory(new PropertyValueFactory<>("fornecedorPedido"));
        tableColumnTotalPe.setCellValueFactory(new PropertyValueFactory<>("totalPedido"));

        listPedido = pedidoDAO.listar();

        observableListPedido = FXCollections.observableArrayList(listPedido);
        tableViewPedido.setItems(observableListPedido);

        /*for(Pedido p:listPedido){
            ArrayList <ProdutoPedido> listProdutoPedido;
            listProdutoPedido = (ArrayList<ProdutoPedido>) produtoPedidoDAO.listarPorPedido(p);
            p.setListProdutoPedido(listProdutoPedido);
            
        }*/
 /*observableListPedido = FXCollections.observableArrayList(listPedido);
        tableViewPedido.setItems(observableListPedido);*/
    }

    public void carregarItensPedido(Pedido pedido) {
        /*tableColumnCodProdPed.setCellValueFactory(new PropertyValueFactory<>("codProduto"));
        tableColumnNomeProdPed.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        tableColumnQtdProdPed.setCellValueFactory(new PropertyValueFactory<>("qtdePedido"));
         */

        tableColumnCodProdPed.setCellValueFactory(new PropertyValueFactory<>("idProdutoPedido"));
        tableColumnNomeProdPed.setCellValueFactory(new PropertyValueFactory<>("codProdutoPedidoProduto"));
        tableColumnQtdProdPed.setCellValueFactory(new PropertyValueFactory<>("qtdePedido"));
        //pedido = tableViewPedido.getSelectionModel().getSelectedItem();
        //listProdutoPedido = produtoPedidoDAO.listarPorPedido(pedido);

        observableListProdutoPedido = FXCollections.observableArrayList(produtoPedidoDAO.listarPorPedido(pedido));
        tableViewProdutosPedidos.setItems(observableListProdutoPedido);

        //System.out.println(pedido.getListProdutoPedido());
    }

    public void selecionarItemTableViewPedido(Pedido pedido) {
        pedido = tableViewPedido.getSelectionModel().getSelectedItem();

        System.out.println("pedido 0"+pedido);
        carregarItensPedido(pedido);

    }

    /*n buttonConfirmarClicked = showFXMLAnchorPaneCadastrosClientesDialog(cliente);
        if (buttonConfirmarClicked) {
            clienteDAO.inserir(cliente);
            carregarTableViewCliente();
    
        }
    }
     */
    @FXML
    public void handleButtonReceber() throws IOException, SQLException {
        Pedido pedido = tableViewPedido.getSelectionModel().getSelectedItem();
        System.out.println("Pedido1" +pedido);
        if (pedido != null) {
            boolean buttonConfirmarClicked = showFXMLRecebimentoProdutoDialog(pedido);
            if (buttonConfirmarClicked) {
                //pedidoDAO.listar();
                //carregarTableViewPedido();
                /*connection.setAutoCommit(false);
                recebimentoDAO.setConnection(connection);
                produtoPedidoDAO.setConnection(connection);
                produtoRecebimentoDAO.setConnection(connection);
                produtoDAO.setConnection(connection);
                
                for(ProdutoPedido listProdutoPedido: pedido.getListProdutoPedido()){
                    Produto produto = listProdutoPedido.getCodProdutoPedidoProduto();
                    listProdutoPedido.setCodProdutoPedidoPedido(pedido);
                }*/
                
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, escolha um Pedido na Tabela!");
                alert.show();
            }
        }
    }

    /*
    @FXML
    public void handleButtonRemover() throws IOException {
        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            clienteDAO.remover(cliente);
            carregarTableViewCliente();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }
     */
    public boolean showFXMLRecebimentoProdutoDialog(Pedido pedido) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        System.out.println("pedido2"+pedido);
        loader.setLocation(FXMLRecebimentoProdutoController.class.getResource("/fxmlrecebimentoprodutos/view/FXMLRecebimentoProduto.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Recebimento");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLRecebimentoProdutoController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPedido(pedido);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }

}
