package repository.custom;

import dto.Product;
import entity.OrderEntity;
import javafx.collections.ObservableList;
import repository.CrudRepository;

public interface OrderDao extends CrudRepository<OrderEntity,String> {
    String invoiceNumber();
    String genarateId();
    ObservableList<String> getProductId();
    ObservableList<String> getSize();
    Product search(String id);
}
