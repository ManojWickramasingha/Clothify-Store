package repository.custom;

import dto.OrderDetail;
import entity.OrderDetailEntity;
import entity.ProductEntity;
import javafx.collections.ObservableList;
import repository.CrudRepository;

import java.sql.ResultSet;

public interface ProductDao extends CrudRepository<ProductEntity,String> {

   Boolean deleteById(String id);
   ProductEntity findById(String id);

   ResultSet genarateIdms();

   Boolean updateStoke(ObservableList<OrderDetailEntity> orderDetailList);


}
