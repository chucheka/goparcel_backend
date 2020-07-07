package com.neulogics.GoParcel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neulogics.GoParcel.model.Role;
import com.neulogics.GoParcel.model.RoleName;



public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(RoleName roleUser);

}
