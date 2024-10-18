package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Register;
import model.User;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
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

    @FXML
    void btnReloadOnAction(ActionEvent event) {

        LoadTable();
    }

    private void LoadTable(){
        ObservableList<Register> registerObservableList = FXCollections.observableArrayList();

        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement("SELECT * FROM register");
            ResultSet resultSet = psTm.executeQuery();



            while(resultSet.next()){
                registerObservableList.add(
                        new Register(
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("role"),
                                resultSet.getString("name")

                        )
                );
            }
            tblRegister.setItems(registerObservableList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnRegisteredOnAction(ActionEvent event) {
        String SQL = "UPDATE register SET role=? WHERE email=?";
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,txtUserRole.getText());
            psTm.setObject(2,txtEmail.getText());
           boolean isUpdate = psTm.executeUpdate()>0;

           if(isUpdate){
               new Alert(Alert.AlertType.CONFIRMATION,"Update successful").show();
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        LoadTable();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String SQL = "DELETE FROM register WHERE email=?";
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,txtEmail.getText());
            boolean isDelete = psTm.executeUpdate()>0;

            if(isDelete){
                new Alert(Alert.AlertType.INFORMATION,"Delete user successful");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        LoadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
