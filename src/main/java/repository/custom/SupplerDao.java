package repository.custom;

import entity.SupplerEntity;
import javafx.collections.ObservableList;
import repository.CrudRepository;

public interface SupplerDao extends CrudRepository<SupplerEntity,String> {

    Boolean deleteById(String id);

    ObservableList<String> idList();
}
