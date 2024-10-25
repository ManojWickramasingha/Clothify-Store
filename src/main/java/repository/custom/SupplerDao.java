package repository.custom;

import entity.SupplerEntity;
import javafx.collections.ObservableList;
import repository.CrudRepository;

import java.sql.ResultSet;

public interface SupplerDao extends CrudRepository<SupplerEntity,String> {

    Boolean deleteById(String id);

    ObservableList<String> idList();

    ResultSet genarateIdms();
}
