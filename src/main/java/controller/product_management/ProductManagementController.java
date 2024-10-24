package controller.product_management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dto.Category;
import dto.OrderDetail;
import dto.Product;
import dto.Suppler;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductManagementController implements ProductManagementService{

    private static ProductManagementController instance;

    private  ProductManagementController(){}

    public static ProductManagementController getInstance(){
        return instance==null?instance = new ProductManagementController():instance;
    }

    private CrudUtil util = CrudUtil.getInstance();
    @Override
    public boolean addProduct(Product product) {
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
    public ObservableList<Suppler> getAllSuppler() {
        ObservableList<Suppler> supplers = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM suppler";
        ResultSet resultSet = util.execute(SQL);
        try {
            while (resultSet.next()){
                supplers.add(
                        new Suppler(
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                resultSet.getString("company"),
                                resultSet.getString("email"),
                                resultSet.getDate("create_date").toLocalDate()
                        )
                );
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        if(!supplers.isEmpty()){
            return  supplers;
        }


        return null;
    }

    @Override
    public Product searchproduct(String id) {
        String SQL = "SELECT * FROM product WHERE id=?";

        ResultSet resultSet = util.execute(SQL, id);
        Product product = null;
        try {
           while (resultSet.next()){
            product =   new Product(resultSet.getString("id"),
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

    @Override
    public boolean deleteProduct(String id) {
        String SQL = "DELETE FROM product WHERE id=?";
        Boolean isDelete = util.execute(SQL, id);

        if(isDelete){
            return  true;
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
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
    public ObservableList<Category> getAllCategory() {
        String SQL = "SELECT * FROM category";
        ResultSet resultSet = util.execute(SQL);
        ObservableList<Category> categories = FXCollections.observableArrayList();
        try {
          while (resultSet.next()){
              categories.add(
                      new Category(
                              resultSet.getInt("id"),
                              resultSet.getString("name")
                      )
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

    @Override
    public Boolean updateStoke(ObservableList<OrderDetail> orderDetailList) {
        for(OrderDetail obj : orderDetailList){
          Boolean isUpdateStock =  updateStoke(obj);
          if(!isUpdateStock){
              return false;
          }
        }
        return true;
    }

    private Boolean updateStoke(OrderDetail orderDetail){
        String SQL = "UPDATE product SET qty=qty-? WHERE id=?";
      return util.execute(SQL,
                orderDetail.getQty(),
                orderDetail.getProductId());
    }

}
