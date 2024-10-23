package controller.employee_management;

import javafx.collections.ObservableList;
import model.Employee;

public interface EmployeeManagementService {

    String genarateIdEm();

    Boolean addEmployee(Employee employee);

    Boolean updateEmployee(Employee employee);
    Boolean deleteEmployee(String id);

    ObservableList<Employee> loadTable();

}
