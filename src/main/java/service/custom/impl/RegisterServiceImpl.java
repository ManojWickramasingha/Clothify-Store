package service.custom.impl;

import repository.DaoFactory;
import repository.custom.RegisterDao;
import service.ServiceFactory;
import service.custom.RegisterService;
import util.DaoType;

public class RegisterServiceImpl implements RegisterService {

    final RegisterDao repository = DaoFactory.getInstance().getDaoType(DaoType.REGISTER);
    @Override
    public boolean userReister(String password, String cPassword, String email, String name) {
        return repository.userRegister(password,cPassword,email,name);
    }
}
