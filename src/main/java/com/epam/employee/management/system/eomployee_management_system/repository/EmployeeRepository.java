package com.epam.employee.management.system.eomployee_management_system.repository;

import com.epam.employee.management.system.eomployee_management_system.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
}
