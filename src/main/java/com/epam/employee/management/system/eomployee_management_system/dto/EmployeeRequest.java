package com.epam.employee.management.system.eomployee_management_system.dto;

import com.epam.employee.management.system.eomployee_management_system.entity.Address;
import com.epam.employee.management.system.eomployee_management_system.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull
    private Integer salary;
    private List<Address> addresses;
    private List<Role> roles;


}
