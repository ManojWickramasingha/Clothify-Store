package repository.custom;


import entity.ProductRangeEntity;
import repository.CrudRepository;

import java.sql.ResultSet;

public interface ProductRangeDao extends CrudRepository<ProductRangeEntity,String> {

    Boolean deleteById(String id);

    String findLastId();

    ResultSet genarateIdms();
}
