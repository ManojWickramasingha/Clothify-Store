package service.custom;

import service.SuperService;

public interface RegisterService extends SuperService {
    public boolean userReister(String password,String cPassword,String email, String name);
}
