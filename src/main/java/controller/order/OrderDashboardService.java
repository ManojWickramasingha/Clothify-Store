package controller.order;

import javafx.collections.ObservableList;
import dto.Cart;
import dto.Order;
import dto.Product;

import java.sql.SQLException;
import java.time.LocalDate;

public interface OrderDashboardService {
    String genarateId();

    LocalDate genarateDate();

    ObservableList<String> getProductId();

    ObservableList<String> getSize();

    Product search(String id);

    ObservableList<Cart> addcartList(Cart cart);

    Double getCalTotal();

    String invoiceNumber();

    Boolean placeOrder(Order order) throws SQLException;


}
