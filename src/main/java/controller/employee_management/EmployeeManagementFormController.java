package controller.employee_management;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeManagementFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Employee> tblEmployee;

    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEmail;

    @FXML
    private JFXTextField txtEmployeeId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearch;

    private EmployeeManagementService service = EmployeeManagementController.getInstance();

    @FXML
    void btnAddOnAction(ActionEvent event) {
     Boolean isAdd =   service.addEmployee(
                new Employee(
                        txtEmployeeId.getText(),
                        txtName.getText(),
                        txtCompany.getText(),
                        txtEmail.getText()
                )
        );

     if(isAdd){
         new Alert(Alert.AlertType.INFORMATION,"Employee Added successful").show();
     }else{
         new Alert(Alert.AlertType.ERROR,"Employee Not Add").show();
     }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
       Boolean isDelete = service.deleteEmployee(txtEmployeeId.getText());
       if(isDelete){
           new Alert(Alert.AlertType.INFORMATION,"Employee delete successful").show();
           tblEmployee.setItems(service.loadTable());
           clear();
       }else {
           new Alert(Alert.AlertType.ERROR,"Employee Delete Fail").show();
           clear();
       }

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
      Boolean isUpdate =  service.updateEmployee(
                new Employee(
                        txtEmployeeId.getText(),
                        txtName.getText(),
                        txtCompany.getText(),
                        txtEmail.getText()
                )
        );
      if(isUpdate){
          new Alert(Alert.AlertType.CONFIRMATION,"Employee Update Successful").show();
          tblEmployee.setItems(service.loadTable());
          clear();
      }else{
          new Alert(Alert.AlertType.ERROR,"Employee Not Update").show();
          clear();
      }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        String id = service.genarateIdEm();
        if(id != null){
            txtEmployeeId.setText(id);
        }else{
            txtEmployeeId.setText("E001");
        }

        tblEmployee.setItems(service.loadTable());

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            if(newVal != null){
                AddTextTo(newVal);
            }
        });

    }

    private void AddTextTo(Employee newVal) {
        txtEmployeeId.setText(newVal.getId());
        txtName.setText(newVal.getName());
        txtCompany.setText(newVal.getCompany());
        txtEmail.setText(newVal.getEmail());
    }

    private void clear(){
        txtEmployeeId.clear();
        txtName.clear();
        txtCompany.clear();
        txtEmail.clear();
    }
}
