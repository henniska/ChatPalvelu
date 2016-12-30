package com.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.domain.Role;
 
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}

