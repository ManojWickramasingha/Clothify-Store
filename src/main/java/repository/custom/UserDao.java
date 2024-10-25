package repository.custom;

import entity.UserEntity;
import repository.CrudRepository;

import java.sql.ResultSet;

public interface UserDao extends CrudRepository<UserEntity,String> {

    boolean registered(String email,String role);
    boolean deleteById(String id);

    ResultSet genarateIdms();
}
