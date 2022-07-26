package com.HOPITALL.HOPITALL.securityConfig.service;

import com.HOPITALL.HOPITALL.securityConfig.entities.AppRole;
import com.HOPITALL.HOPITALL.securityConfig.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username,String password,String rePassword);
    AppRole saveNewRole(String roleName,String description);
    void addRoleToUser(String username,String roleName);
    void removeRolefromUser(String username,String roleName);
    AppUser loadUserByUserName(String username);
}