package service.custom;

import service.SuperService;

public interface LoginService extends SuperService {
    public String LoginUser(String email,String password);
}
