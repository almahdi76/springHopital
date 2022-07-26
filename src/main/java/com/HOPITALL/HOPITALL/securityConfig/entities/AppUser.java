package com.HOPITALL.HOPITALL.securityConfig.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor @AllArgsConstructor @Data
public class AppUser {

    @Id
    private String userId;
    @Column(unique=true)
    private String username;

    private String password;
    private boolean active;
    

        @ManyToMany(fetch = FetchType.EAGER)  // lazy ==== no charge role que quand on appel appRole EAGER en memetemps dans mempire
        private List<AppRole> appRoles=new ArrayList<>();
}
