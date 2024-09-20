package com.epam.employee.management.system.eomployee_management_system.service;


import com.epam.employee.management.system.eomployee_management_system.dto.EmployeeRequest;
import com.epam.employee.management.system.eomployee_management_system.entity.Employee;
import com.epam.employee.management.system.eomployee_management_system.entity.Role;
import com.epam.employee.management.system.eomployee_management_system.repository.EmployeeRepository;
import com.epam.employee.management.system.eomployee_management_system.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setSalary(employeeRequest.getSalary());
        employee.setAddresses(employeeRequest.getAddresses());

        List<Role> assignedRoles = new ArrayList<>();
        for (Role requestRole : employeeRequest.getRoles()) {
            Role role = roleRepository.findByRole(requestRole.getRole());
            if (role != null) {
                assignedRoles.add(role);
            } else {
                 role = new Role();
                 role.setRole(requestRole.getRole());
                 roleRepository.save(role);
                 assignedRoles.add(role);

            }
        }
        employee.setRoles(assignedRoles);

        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
       return employeeRepository.findById(employeeId).orElseThrow(()-> new NoSuchElementException("Employee not found"));
    }

    @Override
    public void updateEmployees(Integer employeeId,@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new NoSuchElementException("Employee not found"));
        employee.setName(employeeRequest.getName());
        employee.setSalary(employeeRequest.getSalary());
        employee.setAddresses(employeeRequest.getAddresses());
        List<Role> assignedRoles = new ArrayList<>();
        for (Role requestRole : employeeRequest.getRoles()) {
            Role role = roleRepository.findByRole(requestRole.getRole());
            if (role != null) {
                assignedRoles.add(role);
            } else {
                role = new Role();
                role.setRole(requestRole.getRole());
                roleRepository.save(role);
                assignedRoles.add(role);
            }
        }
        employee.setRoles(assignedRoles);
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + employeeId));
        employee.getRoles().clear(); // Disassociating roles from the employee
        employeeRepository.save(employee);
        employeeRepository.deleteById(employeeId);
    }
}
