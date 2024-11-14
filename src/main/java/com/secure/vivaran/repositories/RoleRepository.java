package com.secure.vivaran.repositories;

import com.secure.vivaran.models.Role;
import com.secure.vivaran.models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);

}