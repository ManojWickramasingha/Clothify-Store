package repository.custom;

import entity.ProductEntity;
import repository.CrudRepository;

public interface ProductDao extends CrudRepository<ProductEntity,String> {

   Boolean deleteById(String id);
   ProductEntity findById(String id);
}
