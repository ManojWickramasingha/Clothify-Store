package repository.custom;

import entity.RegisterEntity;
import repository.CrudRepository;

public interface RegisterDao extends CrudRepository<RegisterEntity,String> {
    boolean userRegister(String password,String conPassword, String email, String name);
}
