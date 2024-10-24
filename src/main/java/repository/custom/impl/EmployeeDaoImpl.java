package repository.custom.impl;

import dto.Employee;
import entity.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.EmployeeDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDao {

    private CrudUtil util = CrudUtil.getInstance();
    @Override
    public Boolean save(EmployeeEntity employee) {
         System.out.println(employee);
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
    public Boolean update(EmployeeEntity employee) {
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
    public ResultSet genarateIdms() {
        String SQL = "SELECT id from employee order by id DESC LIMIT 1";
        return util.execute(SQL);
    }


    @Override
    public ObservableList<EmployeeEntity> getAll() {
        String SQL = "SELECT * FROM employee";
        ResultSet resultSet = util.execute(SQL);
        ObservableList<EmployeeEntity> employees = FXCollections.observableArrayList();

        try {
            while(resultSet.next()){
                employees.add(
                        new EmployeeEntity(
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

    @Override
    public Boolean deleteById(String id) {
        String SQL = "DELETE FROM employee WHERE id=?";
        Boolean isDelete = util.execute(SQL,id);
        if(isDelete){
            return true;
        }
        return false;

    }
}
