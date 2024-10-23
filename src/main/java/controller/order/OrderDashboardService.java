package controller.order;

import javafx.collections.ObservableList;
import model.Cart;
import model.Order;
import model.Product;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

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
