package repository.custom;

import entity.RegisterEntity;
import repository.CrudRepository;

import java.sql.ResultSet;

public interface RegisterDao extends CrudRepository<RegisterEntity,String> {
    boolean userRegister(String password,String conPassword, String email, String name);
    ResultSet genarateIdms();
}
