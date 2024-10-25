package repository.custom;

import entity.LoginEntity;
import repository.CrudRepository;

import java.sql.ResultSet;

public interface LoginDao extends CrudRepository<LoginEntity,String> {

    String findRole(String email,String password);
    ResultSet genarateIdms();
}
