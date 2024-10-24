package repository;


import javafx.collections.ObservableList;

import java.sql.ResultSet;

public interface CrudRepository<T, S> extends SuperDao {
    Boolean save(T entity);
    Boolean update(T entity);

    ResultSet genarateIdms();

    ObservableList<T> getAll();
}
