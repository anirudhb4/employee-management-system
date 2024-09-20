package com.epam.employee.management.system.eomployee_management_system.controller;

import com.epam.employee.management.system.eomployee_management_system.dto.EmployeeRequest;
import com.epam.employee.management.system.eomployee_management_system.entity.Employee;
import com.epam.employee.management.system.eomployee_management_system.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
@Slf4j
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public void createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        log.info("Employee created: {}", employeeRequest);
        employeeService.createEmployee(employeeRequest);
    }

    @GetMapping
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("{employeeId}")
    public Employee getEmployeeById(@PathVariable Integer employeeId){
        return employeeService.getEmployeeById(employeeId);
    }


    @PutMapping("{employeeId}")
    public void updateEmployees(@PathVariable Integer employeeId, @RequestBody EmployeeRequest employeeRequest){
        employeeService.updateEmployees(employeeId,employeeRequest);
    }

    @DeleteMapping("{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId){
        employeeService.deleteEmployee(employeeId);
    }
}
