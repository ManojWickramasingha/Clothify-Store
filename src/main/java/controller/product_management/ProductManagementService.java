package controller.product_management;

import javafx.collections.ObservableList;
import model.Category;
import model.OrderDetail;
import model.Product;
import model.Suppler;

public interface ProductManagementService {
    boolean addProduct(Product product);
    ObservableList<Suppler> getAllSuppler();

    Product searchproduct(String id);

    boolean deleteProduct(String id);

    boolean updateProduct(Product product);

    ObservableList<Category> getAllCategory();



    Boolean updateStoke(ObservableList<OrderDetail> orderDetailList);
}
