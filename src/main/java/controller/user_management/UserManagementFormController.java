package controller.user_management;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import dto.Register;
import service.ServiceFactory;
import service.custom.UserService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class UserManagementFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableView<Register> tblRegister;

    @FXML
    private TextField txtUserRole;

    @FXML
    private TextField txtEmail;



    final UserService uService = ServiceFactory.getInstance().getServiceType(ServiceType.USER);

    @FXML
    void btnReloadOnAction(ActionEvent event) {

        LoadTable();

    }

    private void LoadTable(){
        ObservableList<Register> allRegister = uService.getAllRegister();
        if(allRegister!=null){
            tblRegister.setItems(allRegister);
        }else{
            new Alert(Alert.AlertType.ERROR,"register user information loaded failed").show();
        }
    }



    @FXML
    void btnRegisteredOnAction(ActionEvent event) {
        boolean isRegistered = uService.confirmRegister(txtEmail.getText(), txtUserRole.getText());
        if(isRegistered){
            new Alert(Alert.AlertType.CONFIRMATION,"Register User Successful").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"User Registered not successful?").show();
        }

        LoadTable();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        boolean isDeleted = uService.deleteUser(txtEmail.getText());
        if(isDeleted){
            new Alert(Alert.AlertType.INFORMATION,"Delete user successful").show();
        }
        LoadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        System.out.println("Start");
        tblRegister.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
           if(newVal!=null){
                setRole(newVal);
           }
        });
    }

    private void setRole(Register register){
        txtUserRole.setText(register.getRole());
        txtEmail.setText(register.getEmail());
    }
}
