package controller.product_range;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ProductRange;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRangeController implements ProductRangeService{

    private static ProductRangeController instance;

    private ProductRangeController(){}

    public static ProductRangeController getInstance(){
        return instance==null? instance = new ProductRangeController():instance;
    }

    private CrudUtil util = CrudUtil.getInstance();
    @Override
    public ObservableList<ProductRange> loadTable() {
        String SQL = "SELECT * FROM category_range";
        ResultSet resultSet = util.execute(SQL);
        ObservableList<ProductRange> productRanges = FXCollections.observableArrayList();
        try {
           while (resultSet.next()){
               productRanges.add(
                       new ProductRange(
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
    public Boolean addRange(ProductRange productRange) {
        String SQL = "INSERT INTO category_range values(?,?,?,?,?)";
       Boolean isAdd = util.execute(SQL,
                productRange.getId(),
                productRange.getType(),
                productRange.getSize(),
                productRange.getStyle(),
                productRange.getCategoryId());
       if(isAdd){
           return true;
       }
        return false;
    }

    @Override
    public String genarateId() {
        String SQL = "SELECT id FROM category_range ORDER BY id DESC LIMIT 1";
       ResultSet resultSet = util.execute(SQL);
        try {
          while(resultSet.next()){
           String lastId =   resultSet.getString("id");
           String subnum = lastId.substring(1);
           Integer number = Integer.parseInt(subnum);
           number++;
           return String.format("R%03d",number);
          }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "R001";
    }

    @Override
    public Boolean deleteRange(String id) {
        String SQL = "DELETE FROM category_range WHERE id=?";
        Boolean isDelete = util.execute(SQL,id);
       if(isDelete){
           return true;
       }
        return false;
    }

    @Override
    public Boolean updateRange(ProductRange productRange) {
        String SQL = "UPDATE category_range SET category_id=?,type=?,size=?,style=? WHERE id=?";
       Boolean isUpdate = util.execute(SQL,
                productRange.getCategoryId(),
                productRange.getType(),
                productRange.getSize(),
                productRange.getStyle(),
               productRange.getId());
       if(isUpdate){
           return true;
       }
        return false;
    }
}
