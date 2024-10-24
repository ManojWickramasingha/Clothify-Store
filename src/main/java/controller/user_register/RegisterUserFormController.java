package controller.user_register;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import dto.User;
import service.ServiceFactory;
import service.custom.RegisterService;
import util.ServiceType;

import java.util.ArrayList;


public class RegisterUserFormController {

    @FXML
    private ImageView imageView;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

   private ArrayList<User> users = new ArrayList<>();

   private RegisterUserService service = RegisterUserController.getInstance();
   final RegisterService regService = ServiceFactory.getInstance().getServiceType(ServiceType.REGISTER);

    @FXML
    void btnRegisterOnAction(ActionEvent event) {


       boolean isAdd = regService.userReister(
                txtPassword.getText(),
                txtConfirmPassword.getText(),
                txtEmail.getText(),
                txtName.getText());

       if(isAdd){
           new Alert(Alert.AlertType.INFORMATION,"User Add successful").show();
       }else{
           new Alert(Alert.AlertType.ERROR,"User added failed").show();
       }








    }




    @FXML
    void btnUploadOnAction(ActionEvent event) {

    }

}
