package controller.user_login;

import db.DbConnection;
import model.Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserLoginController implements UserLoginService{
    public static UserLoginController instance;
    private UserLoginController(){}

    public static UserLoginController getInstance(){
        return instance==null?instance = new UserLoginController():instance;
    }
    @Override
    public String LoginUser(String email,String password) {
        ArrayList<Register> registerArrayList = new ArrayList<>();

        try {
            Connection connection =  DbConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement("SELECT * FROM register");
            ResultSet resultSet = psTm.executeQuery();

            while (resultSet.next()){
                registerArrayList.add(
                        new Register(
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("role"),
                                resultSet.getString("name")
                        )
                );
            }


            for (int i=0;i<registerArrayList.size();i++){
                if((email.equals(registerArrayList.get(i).getEmail()) && (password.equals(registerArrayList.get(i).getPassword())))){
                    if (registerArrayList.get(i).getRole().equals("Admin")){
                        return registerArrayList.get(i).getRole();
                    } else if (registerArrayList.get(i).getRole().equals("User")) {
                        return "User";
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;

    }
}
