package repository.custom.impl;

import dto.Product;
import entity.ProductEntity;
import javafx.collections.ObservableList;
import repository.custom.ProductDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDaoImpl implements ProductDao {

    private CrudUtil util = CrudUtil.getInstance();
    @Override
    public Boolean save(ProductEntity product) {
        String SQL = "INSERT INTO product values(?,?,?,?,?,?,?)";
        Boolean isAdd = util.execute(SQL,
                product.getId(),
                product.getName(),
                product.getSize(),
                product.getPrice(),
                product.getQty(),
                product.getSupperId(),
                product.getCategoryId());

        if(isAdd){
            return  true;
        }
        return false;
    }

    @Override
    public Boolean update(ProductEntity product) {
        String SQL = "UPDATE product SET name=?,size=?,price=?,qty=?,suppler_id=?categoryId=? WHERE id=?";
        boolean isUpdate = util.execute(SQL,
                product.getName(),
                product.getSize(),
                product.getPrice(),
                product.getQty(),
                product.getSupperId(),
                product.getCategoryId(),
                product.getId());
        if(isUpdate){
            return true;
        }
        return false;
    }

    @Override
    public ResultSet genarateIdms() {
        return null;
    }

    @Override
    public ObservableList<ProductEntity> getAll() {
        return null;
    }

    @Override
    public Boolean deleteById(String id) {
        String SQL = "DELETE FROM product WHERE id=?";
        Boolean isDelete = util.execute(SQL, id);

        if(isDelete){
            return  true;
        }
        return false;

    }

    @Override
    public ProductEntity findById(String id) {
        String SQL = "SELECT * FROM product WHERE id=?";

        ResultSet resultSet = util.execute(SQL, id);
        ProductEntity product = null;
        try {
            while (resultSet.next()){
                product =   new ProductEntity(resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("categoryId"),
                        resultSet.getString("size"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("qty"),
                        resultSet.getString("suppler_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        if(product!=null){
            return  product;
        }
        return null;
    }
}
