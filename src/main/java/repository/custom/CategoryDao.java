package repository.custom;

import entity.CategoryEntity;
import javafx.collections.ObservableList;
import repository.CrudRepository;

import java.sql.ResultSet;

public interface CategoryDao extends CrudRepository<CategoryEntity,Integer> {

    ObservableList<Integer> getAllIds();

    ResultSet genarateIdms();
}
