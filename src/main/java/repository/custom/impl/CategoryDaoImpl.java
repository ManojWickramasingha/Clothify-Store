package repository.custom.impl;

import dto.Category;
import entity.CategoryEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.CategoryDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDaoImpl implements CategoryDao {

    private CrudUtil util = CrudUtil.getInstance();
    @Override
    public Boolean save(CategoryEntity entity) {
        return null;
    }

    @Override
    public Boolean update(CategoryEntity entity) {
        return null;
    }

    @Override
    public ResultSet genarateIdms() {
        return null;
    }

    @Override
    public ObservableList<CategoryEntity> getAll() {
        return null;
    }

    @Override
    public ObservableList<Integer> getAllIds() {
        String SQL = "SELECT id FROM category";
        ResultSet resultSet = util.execute(SQL);
        ObservableList<Integer> categories = FXCollections.observableArrayList();
        try {
            while (resultSet.next()){
                categories.add(
                    resultSet.getInt("id")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(!categories.isEmpty()){
            return categories;
        }
        return null;
    }
}
