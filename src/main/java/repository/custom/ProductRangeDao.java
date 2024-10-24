package repository.custom;


import entity.ProductRangeEntity;
import repository.CrudRepository;

public interface ProductRangeDao extends CrudRepository<ProductRangeEntity,String> {

    Boolean deleteById(String id);

    String findLastId();
}
