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
import dto.ProductRange;
import service.ServiceFactory;

import util.ServiceType;

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



    final ProductRangeService rService = ServiceFactory.getInstance().getServiceType(ServiceType.RANGE);

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddTableOnAction() {
      Boolean isAdd =  rService.addProductRange(
                new ProductRange(
                      txtId.getText(),
                      txtType.getText(),
                      txtSize.getText(),
                      txtStyle.getText(),
                     Integer.parseInt(txtCategoryId.getText())
                )
        );
      if(Boolean.TRUE.equals(isAdd)){
          new Alert(Alert.AlertType.INFORMATION,"productRange add successful").show();
      }else{
          new Alert(Alert.AlertType.ERROR,"ProductRange not add").show();
      }
    }

    @FXML
    void btnDeleteOnAction() {
        Boolean isDelete = rService.deleteRange(txtId.getText());
        if(Boolean.TRUE.equals(isDelete)){
            new Alert(Alert.AlertType.CONFIRMATION,"Product Range Delete Successful").show();
            tblProductRange.setItems(rService.loadTable());
        }else {
            new Alert(Alert.AlertType.ERROR,"Product Range Delete Fail").show();
        }
    }

    @FXML
    void btnUpdateOnAction() {
     Boolean isUpdate =   rService.updateRange(
                new ProductRange(
                        txtId.getText(),
                        txtType.getText(),
                        txtSize.getText(),
                        txtStyle.getText(),
                        Integer.parseInt(txtCategoryId.getText())
                )
        );
     if(Boolean.TRUE.equals(isUpdate)){
         new Alert(Alert.AlertType.CONFIRMATION,"Product Range Update Successful").show();
         tblProductRange.setItems(rService.loadTable());
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

        ObservableList<ProductRange> productRanges = rService.loadTable();
        if(productRanges!=null){
                tblProductRange.setItems(productRanges);
        }

        txtId.setText(rService.genarateId());

        tblProductRange.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            if(newVal!=null){
                textToComplete(newVal);
            }
        });
    }

    private void textToComplete(ProductRange newVal) {
        txtId.setText(newVal.getId());
        txtCategoryId.setText(""+newVal.getCategoryId());
        txtType.setText(newVal.getType());
        txtSize.setText(newVal.getSize());
        txtStyle.setText(newVal.getStyle());
    }
}
