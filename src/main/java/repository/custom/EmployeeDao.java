package repository.custom;

import entity.EmployeeEntity;
import repository.CrudRepository;

import java.sql.ResultSet;

public interface EmployeeDao extends CrudRepository<EmployeeEntity,String>{
   Boolean deleteById(String id);
   String genarateId();

   ResultSet genarateIdms();
}
