package com.HOPITALL.HOPITALL.securityConfig.service;



import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HOPITALL.HOPITALL.securityConfig.entities.AppRole;
import com.HOPITALL.HOPITALL.securityConfig.entities.AppUser;
import com.HOPITALL.HOPITALL.securityConfig.repositiries.AppRoleRepository;
import com.HOPITALL.HOPITALL.securityConfig.repositiries.AppUserReposirory;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService{
    private  AppUserReposirory appUserReposirory;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
   
        public AppUser saveNewUser(String username,String password,String rePassword){
            if(!password.equals(rePassword)) throw new RuntimeException("password not match");
            String hashesPWD=passwordEncoder.encode(password);
            AppUser appUser=new AppUser();

            appUser.setUserId(UUID.randomUUID().toString());
            appUser.setUsername(username);
            appUser.setPassword(hashesPWD);
            appUser.setActive(true);
            AppUser savedAppUser=appUserReposirory.save(appUser);
            return savedAppUser;
        }
    public AppRole saveNewRole(String roleName,String description){

        AppRole appRole=appRoleRepository.findByRoleName(roleName );
        if(appRole!=null) throw  new RuntimeException(appRole+" deja existe");
        appRole=new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
         AppRole savedApprole=appRoleRepository.save(appRole);
        return savedApprole;
    }
   
    public void addRoleToUser(String username,String roleName){
        AppUser appUser=appUserReposirory.findByUsername(username);
        if(appUser==null) throw  new RuntimeException(" user not found");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw  new RuntimeException(" this role not found");
        appUser.getAppRoles().add(appRole);
        //appUserReposirory.save(appUser);  Transictional va faire ca

    }
    public void removeRolefromUser(String username,String roleName){
        AppUser appUser=appUserReposirory.findByUsername(username);
        if(appUser==null) throw  new RuntimeException(" user not found");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw  new RuntimeException(" this role not found");
        appUser.getAppRoles().remove(appRole);
       

    }
    public AppUser loadUserByUserName(String username){
        return appUserReposirory.findByUsername(username);
    }
}

