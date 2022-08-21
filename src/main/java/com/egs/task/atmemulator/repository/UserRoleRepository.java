package com.egs.task.atmemulator.repository;

import com.egs.task.atmemulator.model.Role;
import com.egs.task.atmemulator.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByRoleName(UserRole userRole);


}
