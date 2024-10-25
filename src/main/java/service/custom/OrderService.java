package service.custom;

import dto.Cart;
import dto.Order;
import dto.Product;
import javafx.collections.ObservableList;
import service.SuperService;
import java.time.LocalDate;

public interface OrderService extends SuperService {
    String genarateId();

    LocalDate genarateDate();

    ObservableList<String> getProductId();

    ObservableList<String> getSize();

    Product search(String id);

    ObservableList<Cart> addcartList(Cart cart);

    Double getCalTotal();

    String invoiceNumber();

    Boolean placeOrder(Order order);
}
