package controller.product_range;

import javafx.collections.ObservableList;
import dto.ProductRange;

public interface ProductRangeService {

    ObservableList<ProductRange> loadTable();


    String genarateId();

    Boolean deleteRange(String id);

    Boolean updateRange(ProductRange productRange);

    Boolean addProductRange(ProductRange productRange);
}
