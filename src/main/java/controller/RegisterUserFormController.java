package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.User;

import java.util.ArrayList;
import java.util.regex.*;


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

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String password = txtPassword.getText();
        String cPassword = txtConfirmPassword.getText();
        String email =  txtEmail.getText();
        String name = txtName.getText();





        if(password.isEmpty() || name.isEmpty() || email.isEmpty()){
            System.out.println("Text filed are completed");
        }else{

            if(password.equals(cPassword) && isValidPassword(password)){
                if(isValideEmail(email)){
                    users.add( new User(
                            name,
                            email,
                            password
                    ));
                    System.out.println(users);

                } else{
                    System.out.println("Email is not valid");
                }
            }else{
                System.out.println("Password not match or password not valide");
            }

        }



    }
    private boolean isValidPassword(String password){

        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
       return m.matches();
    }

    private boolean isValideEmail(String email){
      String regexPattern = "^(.+)@(\\S+)$";
      Pattern p = Pattern.compile(regexPattern);
      if(email == null){
          return false;
      }
        Matcher matcher = p.matcher(email);
        return  matcher.matches();

    }

    @FXML
    void btnUploadOnAction(ActionEvent event) {

    }

}
