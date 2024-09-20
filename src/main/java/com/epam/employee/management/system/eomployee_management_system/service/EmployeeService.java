package com.epam.employee.management.system.eomployee_management_system.service;

import com.epam.employee.management.system.eomployee_management_system.dto.EmployeeRequest;
import com.epam.employee.management.system.eomployee_management_system.entity.Employee;

import java.util.List;

public interface EmployeeService {
    void createEmployee(EmployeeRequest employeeRequest);
    List<Employee> getEmployees();
    void updateEmployees(Integer employeeId,EmployeeRequest employeeRequest);
    void deleteEmployee(Integer employeeId);
    Employee getEmployeeById(Integer employeeId);
}
