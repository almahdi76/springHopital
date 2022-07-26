package com.HOPITALL.HOPITALL.securityConfig.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.HOPITALL.HOPITALL.securityConfig.entities.AppUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private SecurityService securityService;
    

    public UserDetailsServiceImpl(SecurityService securityService) {
        this.securityService = securityService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=securityService.loadUserByUserName(username);
        /* 
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        appUser.getAppRoles().forEach(role ->{
            SimpleGrantedAuthority authority=new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(authority);
        });
        */
        Collection<SimpleGrantedAuthority>authorities1=
        appUser.getAppRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
        User user=new User(appUser.getUsername(),appUser.getPassword(),authorities1);
        return user;
        
    }
    
}
