package repository.custom.impl;


import entity.ProductRangeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.ProductRangeDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRangeDaoImpl implements ProductRangeDao {

    final CrudUtil util = CrudUtil.getInstance();
    @Override
    public Boolean save(ProductRangeEntity productRange) {
        String SQL = "INSERT INTO category_range values(?,?,?,?,?)";
        return util.execute(SQL,
                productRange.getId(),
                productRange.getType(),
                productRange.getSize(),
                productRange.getStyle(),
                productRange.getCategoryId());

    }

    @Override
    public Boolean update(ProductRangeEntity productRange) {
        String SQL = "UPDATE category_range SET category_id=?,type=?,size=?,style=? WHERE id=?";
        return util.execute(SQL,
                productRange.getCategoryId(),
                productRange.getType(),
                productRange.getSize(),
                productRange.getStyle(),
                productRange.getId());


    }

    @Override
    public ResultSet genarateIdms() {
        return null;
    }


    @Override
    public ObservableList<ProductRangeEntity> getAll() {

        String SQL = "SELECT * FROM category_range";
        ResultSet resultSet = util.execute(SQL);
        ObservableList<ProductRangeEntity> productRanges = FXCollections.observableArrayList();
        try {
            while (resultSet.next()){
                productRanges.add(
                        new ProductRangeEntity(
                                resultSet.getString("id"),
                                resultSet.getString("type"),
                                resultSet.getString("size"),
                                resultSet.getString("style"),
                                resultSet.getInt("category_id")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(!productRanges.isEmpty()){
            return productRanges;
        }
        return null;
    }

    @Override
    public Boolean deleteById(String id) {
        String SQL = "DELETE FROM category_range WHERE id=?";
        return util.execute(SQL,id);

    }

    @Override
    public String findLastId() {
        String SQL = "SELECT id FROM category_range ORDER BY id DESC LIMIT 1";
        ResultSet resultSet = util.execute(SQL);
        try {
            while(resultSet.next()){
                String lastId =   resultSet.getString("id");
                String subNum = lastId.substring(1);
                Integer number = Integer.parseInt(subNum);
                number++;
                return String.format("R%03d",number);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "R001";
    }
}
