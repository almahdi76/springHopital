package com.HOPITALL.HOPITALL.securityConfig;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication()
        // .withUser("almahdi").password("{noop}1234").roles("USER","ADMIN")
        // .and()
        // .withUser("sef").password("{noop}123").roles("USER");
        /* 
        PasswordEncoder passwordEncoder=passwordEncoder();
        String PWD=passwordEncoder.encode("123");
        System.out.println("Abdo\n"+PWD);
        auth.inMemoryAuthentication()
        .withUser("almahdi").password(passwordEncoder.encode("1234")).roles("USER","ADMIN")
        .and()
        .withUser("sef").password(PWD).roles("USER");
        */

        PasswordEncoder passwordEncoder=passwordEncoder();
        auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("select username as principal,password as credentials,isactive from users where username=?")
        .authoritiesByUsernameQuery("select usename as principal,rolle as role,from users_rolles where username=?")
        .rolePrefix("ROLE_")
        .passwordEncoder(passwordEncoder);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       
        //http.formLogin().loginPage("/nomPg");
        http.formLogin();
        http.authorizeHttpRequests().antMatchers("/").permitAll();
        // http.authorizeHttpRequests().antMatchers("/delete/**","/edit/**","/save/**","/formPatients/**").hasRole("ADMIN");
        // http.authorizeHttpRequests().antMatchers("/index/**").hasRole("USER");
        http.authorizeHttpRequests().antMatchers("/admin/**").hasRole("ADMIN");
        http.authorizeHttpRequests().antMatchers("/user/**").hasRole("USER");
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    
}