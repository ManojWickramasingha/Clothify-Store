package controller.product_management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import dto.Product;
import service.ServiceFactory;
import service.custom.ProductService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProductManagementFormController implements Initializable {

    @FXML
    private ComboBox<String> cmdSupplerId;

    @FXML
    private ComboBox<Integer> cmdCategory;



    @FXML
    private TextField txtCategory1;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtPrice1;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtProductId1;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtProductName1;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtQuantity1;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtSize;

    @FXML
    private TextField txtSize1;



    @FXML
    private TextField txtSupplerId1;

    private Stage stageRange=null;

    final ProductService pService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    @FXML
    void btnNextOnAction() {
        if (stageRange==null){
            stageRange = new Stage();
            try {
                stageRange.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../view/product_range.fxml")))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stageRange.show();

    }


    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction() {
        boolean isDelete = pService.deleteProduct(txtProductId1.getText());
        if(isDelete){
            new Alert(Alert.AlertType.CONFIRMATION,"Product delete successful").show();
            clearTextTo1();
        }else{
            new Alert(Alert.AlertType.ERROR,"Product delete fail");
        }



    }
    private void clearTextTo1(){
        txtProductId1.clear();
        txtProductName1.clear();
        txtCategory1.clear();
        txtSize1.clear();
        txtPrice1.clear();
        txtQuantity1.clear();
        txtSupplerId1.clear();
    }

    private void clearTextTo(){
        txtProductId.clear();
        txtProductName.clear();
        txtSize.clear();
        txtPrice.clear();
        txtQuantity.clear();
        cmdSupplerId.setPromptText("Suppler id");
        cmdCategory.setPromptText("Category");
    }

    @FXML
    void btnSearchOnAction() {

        Product product = pService.searchproduct(txtSearch.getText());
        setTextTo(product);

    }

    private void setTextTo(Product product){
        txtProductId1.setText(product.getId());
        txtProductName1.setText(product.getName());
        txtCategory1.setText(""+product.getCategoryId());
        txtSize1.setText(product.getSize());
        txtPrice1.setText(""+product.getPrice());
        txtQuantity1.setText(""+product.getQty());
        txtSupplerId1.setText(product.getSupperId());
    }

    @FXML
    void btnUpdateOnAction() {
      boolean isUpdate =  pService.updateProduct(
                new Product(
                        txtProductId1.getText(),
                        txtProductName1.getText(),
                       Integer.parseInt(txtCategory1.getText()),
                        txtSize.getText(),
                       Double.parseDouble(txtPrice.getText()),
                       Integer.parseInt(txtQuantity1.getText()),
                        txtSupplerId1.getText()
                )
        );
      if(isUpdate){
          new Alert(Alert.AlertType.CONFIRMATION,"product update successful").show();
          clearTextTo1();
      }else{
          new Alert(Alert.AlertType.ERROR,"product update fail").show();
          clearTextTo1();
      }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmdSupplerId.setItems(pService.getAllSupplerId());
        ObservableList<Integer> allCategoryId = pService.getAllCategoryId();
        cmdCategory.setItems(allCategoryId);

    }
    @FXML
    void btnAddOnAction() {
       boolean isAdd = pService.addProduct(
                new Product(
                        txtProductId.getText(),
                        txtProductName.getText(),
                        cmdCategory.getValue(),
                        txtSize.getText(),
                        Double.parseDouble(txtPrice.getText()) ,
                        Integer.parseInt(txtQuantity.getText()),
                        cmdSupplerId.getValue()
                )
        );
      if(isAdd){
          new Alert(Alert.AlertType.CONFIRMATION," new product add successful").show();
          clearTextTo();
      }else{
          new Alert(Alert.AlertType.ERROR,"new product add fail").show();
      }
    }
}
