package repository.custom.impl;

import dto.Register;
import entity.LoginEntity;
import javafx.collections.ObservableList;
import repository.custom.LoginDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDaoImpl implements LoginDao {

    private CrudUtil util = CrudUtil.getInstance();
    @Override
    public Boolean save(LoginEntity entity) {
        return null;
    }

    @Override
    public Boolean update(LoginEntity entity) {
        return null;
    }

    @Override
    public ResultSet genarateIdms() {
        return null;
    }

    @Override
    public ObservableList<LoginEntity> getAll() {
        return null;
    }

    @Override
    public String findRole(String email,String password) {
        ArrayList<Register> registerArrayList = new ArrayList<>();

        try {
            String SQL = "SELECT * FROM register";
            ResultSet resultSet = util.execute(SQL);

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
