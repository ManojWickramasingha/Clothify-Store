package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardFormController {

    private Stage stageUser;

    @FXML
    void btnEmployeeManagementOnAction(ActionEvent event) {

    }

    @FXML
    void btnOrderManagementOnAction(ActionEvent event) {

    }

    @FXML
    void btnProductManagementOnAction(ActionEvent event) {

    }

    @FXML
    void btnReportAndAnalyticsOnAction(ActionEvent event) {

    }

    @FXML
    void btnUserManagementOnAction(ActionEvent event) {
        if(stageUser==null){
            stageUser = new Stage();
            try {
                stageUser.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/user_management_form.fxml"))));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stageUser.show();
    }

}
