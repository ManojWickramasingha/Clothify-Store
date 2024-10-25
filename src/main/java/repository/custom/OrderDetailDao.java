package repository.custom;

import dto.OrderDetail;
import entity.OrderDetailEntity;
import javafx.collections.ObservableList;
import repository.CrudRepository;

public interface OrderDetailDao extends CrudRepository<OrderDetailEntity,String> {
    Boolean addOrderDetails(ObservableList<OrderDetailEntity> orderDetails);
}
