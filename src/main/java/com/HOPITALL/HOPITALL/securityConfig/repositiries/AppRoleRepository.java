package com.HOPITALL.HOPITALL.securityConfig.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HOPITALL.HOPITALL.securityConfig.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {

    AppRole  findByRoleName(String roleName);

    
}
