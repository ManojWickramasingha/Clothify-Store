package service.custom;

import javafx.collections.ObservableList;
import service.SuperService;

public interface CategorySevice extends SuperService {

    ObservableList<String> getAllId();
}
