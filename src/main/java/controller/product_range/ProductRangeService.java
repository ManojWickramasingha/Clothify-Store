package controller.product_range;

import javafx.collections.ObservableList;
import model.ProductRange;

public interface ProductRangeService {

    ObservableList<ProductRange> loadTable();
    Boolean addRange(ProductRange productRange);

    String genarateId();

    Boolean deleteRange(String id);

    Boolean updateRange(ProductRange productRange);
}
