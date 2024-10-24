package repository.custom;

import entity.LoginEntity;
import repository.CrudRepository;

public interface LoginDao extends CrudRepository<LoginEntity,String> {

    String findRole(String email,String password);
}
