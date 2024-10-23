package controller.employee_management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeManagementController implements EmployeeManagementService{
    private static EmployeeManagementController instance;

    private EmployeeManagementController(){}

    public static EmployeeManagementController getInstance(){
        return instance==null?instance = new EmployeeManagementController(): instance;
    }

    private CrudUtil util = CrudUtil.getInstance();
    @Override
    public String genarateIdEm() {
        String SQL = "SELECT id from employee order by id DESC LIMIT 1";
        ResultSet resultSet = util.execute(SQL);

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
        String SQL = "Insert into employee values(?,?,?,?)";
      Boolean isAdd =  util.execute(SQL,
                employee.getId(),
                employee.getName(),
                employee.getCompany(),
                employee.getEmail());
      if(isAdd){
          return true;
      }
        return false;
    }

    @Override
    public Boolean updateEmployee(Employee employee) {
        String SQL = "UPDATE employee SET name=?,company=?,email=? WHERE id=?";
       Boolean isUpdate = util.execute(SQL,
                employee.getName(),
                employee.getCompany(),
                employee.getEmail(),
               employee.getId());
       if(isUpdate){
           return true;
       }
        return false;
    }

    @Override
    public Boolean deleteEmployee(String id) {
        String SQL = "DELETE FROM employee WHERE id=?";
       Boolean isDelete = util.execute(SQL,id);
       if(isDelete){
           return true;
       }
        return false;
    }

    @Override
    public ObservableList<Employee> loadTable() {
        String SQL = "SELECT * FROM employee";
        ResultSet resultSet = util.execute(SQL);
        ObservableList<Employee> employees = FXCollections.observableArrayList();

        try {
           while(resultSet.next()){
               employees.add(
                       new Employee(
                               resultSet.getString("id"),
                               resultSet.getString("name"),
                               resultSet.getString("company"),
                               resultSet.getString("email")
                       )
               );
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(!employees.isEmpty()){
            return employees;
        }
        return null;
    }
}
