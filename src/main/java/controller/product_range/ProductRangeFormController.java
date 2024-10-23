package controller.product_range;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ProductRange;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductRangeFormController implements Initializable {
    @FXML
    private TextField txtId;

    @FXML
    private TextField txtCategoryId;

    @FXML
    private TableColumn<?, ?> colCategoryId;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colStyle;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<ProductRange> tblProductRange;

    @FXML
    private JFXTextField txtSize;

    @FXML
    private JFXTextField txtStyle;

    @FXML
    private JFXTextField txtType;

    @FXML
    private TableColumn<?, ?> colId;

    private ProductRangeController service = ProductRangeController.getInstance();

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddTableOnAction(ActionEvent event) {
      Boolean isAdd =  service.addRange(
                new ProductRange(
                      txtId.getText(),
                      txtType.getText(),
                      txtSize.getText(),
                      txtStyle.getText(),
                     Integer.parseInt(txtCategoryId.getText())
                )
        );
      if(isAdd){
          new Alert(Alert.AlertType.INFORMATION,"productRange add successful").show();
      }else{
          new Alert(Alert.AlertType.ERROR,"ProductRange not add").show();
      }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Boolean isDelete = service.deleteRange(txtId.getText());
        if(isDelete){
            new Alert(Alert.AlertType.CONFIRMATION,"Product Range Delete Successful").show();
            tblProductRange.setItems(service.loadTable());
        }else {
            new Alert(Alert.AlertType.ERROR,"Product Range Delete Fail").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
     Boolean isUpdate =   service.updateRange(
                new ProductRange(
                        txtId.getText(),
                        txtType.getText(),
                        txtSize.getText(),
                        txtStyle.getText(),
                        Integer.parseInt(txtCategoryId.getText())
                )
        );
     if(isUpdate){
         new Alert(Alert.AlertType.CONFIRMATION,"Product Range Update Successful").show();
         tblProductRange.setItems(service.loadTable());
     }else{
         new Alert(Alert.AlertType.ERROR,"Product Range Update fail").show();
     }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colStyle.setCellValueFactory(new PropertyValueFactory<>("style"));
        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));

        ObservableList<ProductRange> productRanges = service.loadTable();
        if(productRanges!=null){
                tblProductRange.setItems(productRanges);
        }

        txtId.setText(service.genarateId());

        tblProductRange.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            if(newVal!=null){
                TextToComplte(newVal);
            }
        });
    }

    private void TextToComplte(ProductRange newVal) {
        txtId.setText(newVal.getId());
        txtCategoryId.setText(""+newVal.getCategoryId());
        txtType.setText(newVal.getType());
        txtSize.setText(newVal.getSize());
        txtStyle.setText(newVal.getStyle());
    }
}
