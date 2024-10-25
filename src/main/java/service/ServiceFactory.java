package service;

import service.custom.impl.*;
import util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance==null?instance = new ServiceFactory():instance;
    }

    public <T extends SuperService>T getServiceType(ServiceType type){
        switch (type){
            case PRODUCT: return (T) new ProductServiceImpl();
            case EMPLOYEE:return (T) new EmployeeServiceImpl();
            case SUPPLER:return (T) new SupplerServiceImpl();
            case CATEGORY:return (T) new CategoryServiceImpl();
            case RANGE:return (T) new productRangeServiceImpl();
            case LOGIN:return (T) new LoginServiceImpl();
            case USER:return (T) new UserServiceImpl();
            case REGISTER:return (T) new RegisterServiceImpl();
            case ORDER:return (T) new OrderServiceImpl();
        }
        return null;
    }
}
