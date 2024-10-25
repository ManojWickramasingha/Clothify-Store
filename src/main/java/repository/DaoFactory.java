package repository;

import repository.custom.impl.*;
import util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}
    public static DaoFactory getInstance(){
        return instance==null? instance = new DaoFactory(): instance;
    }

    public <T extends SuperDao>T getDaoType(DaoType type){
        switch(type){
            case PRODUCT:return (T) new ProductDaoImpl();
            case EMPLOYEE:return (T) new EmployeeDaoImpl();
            case CATEGORY:return (T) new CategoryDaoImpl();
            case RANGE:return (T) new ProductRangeDaoImpl();
            case LOGIN:return (T) new LoginDaoImpl();
            case USER:return (T) new UserDaoImpl();
            case REGISTER:return (T) new RegisterDaoImpl();
            case ORDER:return (T) new OrderDaoImpl();
            case ORDERDETAILS: return (T) new OrderDetailDaoImpl();
            case SUPPLER:return (T) new SupplerDaoImpl();
        }
        return null;
    }
}
