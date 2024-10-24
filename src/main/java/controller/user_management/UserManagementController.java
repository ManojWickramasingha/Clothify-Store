package controller.user_management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dto.Register;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManagementController implements UserManagementService{
    private static UserManagementController instance;

    private CrudUtil util = CrudUtil.getInstance();

    private UserManagementController(){}

    public static UserManagementController getInstance(){
        return instance==null?instance = new UserManagementController():instance;
    }
    @Override
    public ObservableList<Register> getAllRegister() {
        ObservableList<Register> registerObservableList = FXCollections.observableArrayList();



        try {
           String SQL = "SELECT * FROM register";
            ResultSet resultSet = util.execute(SQL);



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

            if(!registerObservableList.isEmpty()){
                return registerObservableList;
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean confirmRegister(String email, String role) {
        String SQL = "UPDATE register SET role=? WHERE email=?";
        Boolean isUpdate = util.execute(SQL, role, email);
        if(isUpdate){
           return true;
        }

        return false;
    }

    @Override
    public boolean deleteUser(String email) {
        String SQL = "DELETE FROM register WHERE email=?";


        Boolean isDelete = util.execute(SQL,email);

        if(isDelete){
            return true;
        }

        return false;
    }
}
