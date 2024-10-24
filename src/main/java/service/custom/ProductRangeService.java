package service.custom;

import dto.ProductRange;
import javafx.collections.ObservableList;
import service.SuperService;

public interface ProductRangeService extends SuperService {
    ObservableList<ProductRange> loadTable();
    Boolean addProductRange(ProductRange productRange);

    String genarateId();

    Boolean deleteRange(String id);

    Boolean updateRange(ProductRange productRange);
}
