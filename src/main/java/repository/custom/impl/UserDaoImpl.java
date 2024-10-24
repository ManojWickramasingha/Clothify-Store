package repository.custom.impl;

import dto.Register;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.UserDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private CrudUtil util = CrudUtil.getInstance();
    @Override
    public Boolean save(UserEntity entity) {
        return null;
    }

    @Override
    public Boolean update(UserEntity entity) {
        return null;
    }

    @Override
    public ResultSet genarateIdms() {
        return null;
    }

    @Override
    public ObservableList<UserEntity> getAll() {
        ObservableList<UserEntity> registerObservableList = FXCollections.observableArrayList();



        try {
            String SQL = "SELECT * FROM register";
            ResultSet resultSet = util.execute(SQL);



            while(resultSet.next()){
                registerObservableList.add(
                        new UserEntity(
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
    public boolean registered(String email, String role) {
        String SQL = "UPDATE register SET role=? WHERE email=?";
        return util.execute(SQL, role, email);

    }

    @Override
    public boolean deleteById(String id) {
        String SQL = "DELETE FROM register WHERE email=?";
        return util.execute(SQL,id);


    }
}
