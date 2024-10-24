package repository.custom.impl;

import dto.Suppler;
import entity.SupplerEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.SupplerDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplerDaoImpl implements SupplerDao {

    private CrudUtil util = CrudUtil.getInstance();
    @Override
    public Boolean save(SupplerEntity suppler) {
        String SQL = "INSERT INTO suppler VALUES(?,?,?,?,?)";
        return util.execute(SQL,
                suppler.getId(),
                suppler.getName(),
                suppler.getCompany(),
                suppler.getEmail(),
                suppler.getCreateDate());

    }

    @Override
    public Boolean update(SupplerEntity suppler) {
        String SQL = "UPDATE suppler SET name=?,company=?,email=?";
       return util.execute(SQL,suppler.getName(),suppler.getCompany(),suppler.getEmail());

    }

    @Override
    public ResultSet genarateIdms() {
        return null;
    }

    @Override
    public ObservableList<SupplerEntity> getAll() {
        return null;
    }

    @Override
    public Boolean deleteById(String id) {
        String SQL = "DELETE FROM suppler WHERE id=?";
        return util.execute(SQL,id);

    }

    @Override
    public ObservableList<String> idList() {

        ObservableList<String> Ids = FXCollections.observableArrayList();

        String SQL = "SELECT id FROM suppler";
        ResultSet resultSet = util.execute(SQL);
        try {
            while (resultSet.next()){
                Ids.add(
                        resultSet.getString("id"));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        if(!Ids.isEmpty()){
            return  Ids;
        }


        return null;
    }
}
