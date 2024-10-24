package service.custom.impl;

import repository.DaoFactory;
import repository.custom.LoginDao;
import service.custom.LoginService;
import util.DaoType;

public class LoginServiceImpl implements LoginService {

    final LoginDao repository = DaoFactory.getInstance().getDaoType(DaoType.LOGIN);
    @Override
    public String LoginUser(String email, String password) {
        return repository.findRole(email,password);
    }
}
