package com.HOPITALL.HOPITALL.securityConfig;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserReposirory extends JpaRepository <AppUser,String>{

    AppUser findByUsername(String username);
    
}
