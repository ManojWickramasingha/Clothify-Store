package service;

import service.custom.impl.EmployeeServiceImpl;
import service.custom.impl.ProductServiceImpl;
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
        }
        return null;
    }
}
