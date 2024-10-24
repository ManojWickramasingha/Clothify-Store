package service.custom.impl;

import entity.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dto.Employee;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDao;
import service.custom.EmployeeService;
import util.DaoType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao repository = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
    ModelMapper mapper = new ModelMapper();
    @Override
    public String genarateIdEm() {
        ResultSet resultSet = repository.genarateIdms();
        String Id = null;
        try {
            while(resultSet.next()){
                Id =  resultSet.getString("id");
                String last = Id.substring(1);
                int number = Integer.parseInt(last);
                number++;
                return String.format("E%03d", number);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;


    }

    @Override
    public Boolean addEmployee(Employee employee) {
        System.out.println(employee);
      Boolean isAdd =  repository.save(mapper.map(employee, EmployeeEntity.class));
      if(isAdd){
          return true;
      }
      return false;
    }

    @Override
    public Boolean updateEmployee(Employee employee) {

       Boolean isUpdate = repository.update(mapper.map(employee,EmployeeEntity.class));
       if(isUpdate){
           return true;
       }
        return false;
    }

    @Override
    public Boolean deleteEmployee(String id) {
       Boolean isDelete = repository.deleteById(id);
       if(isDelete){
          return true;
       }
        return false;
    }

    @Override
    public ObservableList<Employee> loadTable() {
        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
        ObservableList<EmployeeEntity> allEmployee = repository.getAll();
        for(EmployeeEntity entity: allEmployee){
            employeeObservableList.add(mapper.map(entity,Employee.class));
        }
        if(!allEmployee.isEmpty()){
            return employeeObservableList;
        }
        return null;
    }
}
