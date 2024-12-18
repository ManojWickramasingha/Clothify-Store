package controller.user_management;

import javafx.collections.ObservableList;
import dto.Register;

public interface UserManagementService {
    public ObservableList<Register> getAllRegister();
    public boolean confirmRegister(String email, String role);

    public boolean deleteUser(String email);
}
