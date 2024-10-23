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
import model.Category;
import model.Product;
import model.Suppler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductManagementFormController implements Initializable {

    @FXML
    private ComboBox<String> cmdSupplerId;

    @FXML
    private ComboBox<Integer> cmdCategory;

    @FXML
    private TextField txtCategory;

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
    private TextField txtSupplerId;

    @FXML
    private TextField txtSupplerId1;

    private Stage stageRange=null;

    private ProductManagementService service = ProductManagementController.getInstance();

    @FXML
    void btnNextOnAction(ActionEvent event) {
        if (stageRange==null){
            stageRange = new Stage();
            try {
                stageRange.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/product_range.fxml"))));
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
    void btnDeleteOnAction(ActionEvent event) {
        boolean isDelete = service.deleteProduct(txtProductId1.getText());
        if(isDelete){
            new Alert(Alert.AlertType.CONFIRMATION,"Product delete successful").show();
            clearTextTo();
        }else{
            new Alert(Alert.AlertType.ERROR,"Product delete fail");
        }



    }
    private void clearTextTo(){
        txtProductId1.clear();
        txtProductName1.clear();
        txtCategory1.clear();
        txtSize1.clear();
        txtPrice1.clear();
        txtQuantity1.clear();
        txtSupplerId1.clear();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        Product product = service.searchproduct(txtSearch.getText());
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
    void btnUpdateOnAction(ActionEvent event) {
      boolean isUpdate =  service.updateProduct(
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
      }else{
          new Alert(Alert.AlertType.ERROR,"product update fail").show();
      }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> ids = FXCollections.observableArrayList();
        ObservableList<Suppler> allSuppler = service.getAllSuppler();
        allSuppler.forEach(obj -> {
            ids.add(obj.getId());
        });
        cmdSupplerId.setItems(ids);

       ObservableList<Integer> Categories = FXCollections.observableArrayList();
        ObservableList<Category> allCategory = service.getAllCategory();

        allCategory.forEach(obj ->{
            Categories.add(obj.getId());
        });

        cmdCategory.setItems(Categories);

    }
    @FXML
    void btnAddOnAction(ActionEvent event) {
      Boolean isAdd = service.addProduct(
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
