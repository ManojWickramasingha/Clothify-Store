package service.custom;

import dto.Register;
import entity.UserEntity;
import javafx.collections.ObservableList;
import service.SuperService;

public interface UserService extends SuperService {
    public ObservableList<Register> getAllRegister();
    public boolean confirmRegister(String email, String role);

    public boolean deleteUser(String email);
}
