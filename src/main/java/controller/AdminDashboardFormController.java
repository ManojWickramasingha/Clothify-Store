package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardFormController {

    private Stage stageUser;
    private Stage stageProduct;

    private Stage stageEmployee;

    private Stage stageOrder;

    @FXML
    void btnEmployeeManagementOnAction(ActionEvent event) {
            if(stageEmployee==null){
                stageEmployee = new Stage();
                try {
                    stageEmployee.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/employee_managment.fxml"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            stageEmployee.show();
    }

    @FXML
    void btnOrderManagementOnAction(ActionEvent event) {
        if(stageOrder==null){
            stageOrder = new Stage();
            try {
                stageOrder.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/order_dashboard.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stageOrder.show();
    }

    @FXML
    void btnProductManagementOnAction(ActionEvent event) {
        if (stageProduct==null){
            stageProduct = new Stage();
            try {
                stageProduct.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/product_management.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stageProduct.show();

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
