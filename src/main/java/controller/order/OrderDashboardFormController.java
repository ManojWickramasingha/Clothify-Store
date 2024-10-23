package controller.order;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.*;

import java.net.URL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class OrderDashboardFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmdProductId;

    @FXML
    private JFXComboBox<String> cmdSize;

    @FXML
    private TableColumn<?, ?> colNo;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private Label lblInvoice;

    @FXML
    private JFXTextField lblUser;

    @FXML
    private TableView<Cart> tblOrder;

    @FXML
    private JFXTextField txtCategory;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtID;

    @FXML
    private JFXTextField txtNetTotal;

    @FXML
    private TextField txtPrice;

    @FXML
    private JFXTextField txtProductName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtTime;

    private OrderDashboardController service = OrderDashboardController.getInstance();

    private ObservableList<Cart> orderDetails;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        lblInvoice.setText(service.invoiceNumber());

        txtID.setText(service.genarateId());
        txtDate.setText(service.genarateDate().toString());


            Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
                LocalTime now = LocalTime.now();
                txtTime.setText(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
            }),
                    new KeyFrame(Duration.seconds(1))
            );
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

        ObservableList<String> productId = service.getProductId();
        if(productId!=null){
            cmdProductId.setItems(productId);
        }

        ObservableList<String> size = service.getSize();
        if(size!=null){
            cmdSize.setItems(size);
        }

        cmdProductId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) ->{
            Product search = service.search(newVal);
            TextTo(search);
        } );

        }

    private void TextTo(Product search) {
        txtProductName.setText(search.getName());
        txtCategory.setText(search.getCategoryId().toString());
        txtStock.setText(search.getQty().toString());
        txtPrice.setText(search.getPrice().toString());
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        if(Integer.parseInt(txtQty.getText()) >Integer.parseInt(txtStock.getText())){
            new Alert(Alert.AlertType.ERROR,"Invalid QTY").show();
            return;
        }

        orderDetails = service.addcartList(
                new Cart(
                        cmdProductId.getValue(),
                        cmdSize.getValue(),
                        Integer.parseInt(txtQty.getText()),
                        Double.parseDouble(txtPrice.getText()) * Integer.parseInt(txtQty.getText())
                )
        );
        tblOrder.setItems(orderDetails);

        txtNetTotal.setText(service.getCalTotal().toString());

    }

    public void btnPlaceOnAction(ActionEvent actionEvent) {
        ObservableList<OrderDetail> orderDetails1 = FXCollections.observableArrayList();

       orderDetails.forEach(cart -> {
           orderDetails1.add(
                   new OrderDetail(
                           txtID.getText(),
                           cart.getProductId(),
                           cart.getSize(),
                           cart.getQty()
                   )

           );
       });

     PlaceOrder placeOrder =  new PlaceOrder(
               txtID.getText(),
               lblInvoice.getText()
       );

      Order order =  new Order(
                txtID.getText(),
                LocalDate.parse(txtDate.getText()),
                LocalTime.parse(txtTime.getText()),
                orderDetails1,
                placeOrder
        );
        service.placeOrder(order);
    }
}
