package service.custom.impl;

import dto.Register;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.UserDao;
import service.custom.UserService;
import util.DaoType;

public class UserServiceImpl implements UserService {

    final UserDao repository = DaoFactory.getInstance().getDaoType(DaoType.USER);

    final ModelMapper mapper = new ModelMapper();
    @Override
    public ObservableList<Register> getAllRegister() {
        ObservableList<Register> registers = FXCollections.observableArrayList();
        ObservableList<UserEntity> userEnityList = repository.getAll();
        for(UserEntity entity : userEnityList){
            registers.add(mapper.map(entity, Register.class));
        }
        return registers;
    }

    @Override
    public boolean confirmRegister(String email, String role) {
        return repository.registered(email,role);
    }

    @Override
    public boolean deleteUser(String email) {
        return repository.deleteById(email);
    }
}
