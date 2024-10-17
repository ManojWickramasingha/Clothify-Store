package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartFormController {

    private Stage stage = null;

    @FXML
    void btnGetStartOnAction(ActionEvent event) {

        if(stage==null){
            stage = new Stage();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/user_login.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stage.show();
    }

}
