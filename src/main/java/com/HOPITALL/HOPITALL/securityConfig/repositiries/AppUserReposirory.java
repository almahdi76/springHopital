package com.HOPITALL.HOPITALL.securityConfig.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HOPITALL.HOPITALL.securityConfig.entities.AppUser;

public interface AppUserReposirory extends JpaRepository <AppUser,String>{

    AppUser findByUsername(String username);
    
}
