package com.epam.employee.management.system.eomployee_management_system.repository;

import com.epam.employee.management.system.eomployee_management_system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRole(String role);
}
