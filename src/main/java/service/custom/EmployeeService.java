package service.custom;

import javafx.collections.ObservableList;
import dto.Employee;
import service.SuperService;

public interface EmployeeService extends SuperService  {
    String genarateIdEm();

    Boolean addEmployee(Employee employee);

    Boolean updateEmployee(Employee employee);
    Boolean deleteEmployee(String id);

    ObservableList<Employee> loadTable();
}
