package controller.suppler_management;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Suppler;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SupplerManagementFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colItemID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Suppler> tblSuppler;

    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXTextField txtSupplerId;

    private SupplerManagementController service = SupplerManagementController.getInstance();

    @FXML
    void btnAddOnAction(ActionEvent event) {
      Boolean isAdd =  service.addSuppler(
                new Suppler(
                        txtSupplerId.getText(),
                        txtName.getText(),
                        txtCompany.getText(),
                        txtEmail.getText(),
                        LocalDate.parse(txtDate.getText())
                )
        );

      if(isAdd){
          new Alert(Alert.AlertType.INFORMATION,"Suppler Added is successful").show();
      }else{
          new Alert(Alert.AlertType.ERROR,"Suppler Added Fail");
      }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
       Boolean isDelete = service.deleteSuppler(txtSupplerId.getText());
       if(isDelete){
           new Alert(Alert.AlertType.INFORMATION,"Dlete SucessFul").show();
           Clear();
       }else{
           new Alert(Alert.AlertType.ERROR,"Delete Not Successful").show();
           Clear();
       }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
      Boolean isUpdate =  service.updateSuppler(
                new Suppler(
                        txtSupplerId.getText(),
                        txtName.getText(),
                        txtCompany.getText(),
                        txtEmail.getText(),
                       LocalDate.parse(txtDate.getText())
                )
        );
      if(isUpdate){
          new Alert(Alert.AlertType.INFORMATION,"Suppler update success").show();
          tblSuppler.setItems( service.loadTable());
          Clear();
      }else{
          new Alert(Alert.AlertType.ERROR,"Suppler update fail").show();
          Clear();
      }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colItemID.setCellValueFactory(new PropertyValueFactory<>("createDate"));

        txtSupplerId.setText(service.genarateId());
        tblSuppler.setItems(service.loadTable());

        tblSuppler.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newVal) ->{
            if(newVal != null){
                AddTextTo(newVal);
            }
        } );
    }

    private void AddTextTo(Suppler newVal) {
        txtName.setText(newVal.getName());
        txtCompany.setText(newVal.getCompany());
        txtEmail.setText(newVal.getEmail());
        txtDate.setText(newVal.getCreateDate().toString());
        txtSupplerId.setText(newVal.getId());
    }

    private void Clear(){
        txtSupplerId.clear();
        txtCompany.clear();
        txtEmail.clear();
        txtDate.clear();
        txtSupplerId.clear();
        txtName.clear();
    }
}
