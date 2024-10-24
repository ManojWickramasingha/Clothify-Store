package repository.custom;

import entity.EmployeeEntity;
import repository.CrudRepository;

public interface EmployeeDao extends CrudRepository<EmployeeEntity,String>{
   Boolean deleteById(String id);
}
