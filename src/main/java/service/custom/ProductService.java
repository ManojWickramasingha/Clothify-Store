package service.custom;

import dto.Category;
import dto.OrderDetail;
import dto.Product;
import dto.Suppler;
import javafx.collections.ObservableList;
import service.SuperService;

public interface ProductService extends SuperService {
    boolean addProduct(Product product);
    ObservableList<String> getAllSupplerId();

    Product searchproduct(String id);

    boolean deleteProduct(String id);

    boolean updateProduct(Product product);

    ObservableList<Integer> getAllCategoryId();


}
