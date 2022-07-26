package com.HOPITALL.HOPITALL.securityConfig;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.HOPITALL.HOPITALL.securityConfig.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
   private UserDetailsServiceImpl userDetailsService;

   @Autowired
   private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /* memory auth */

       
        auth.inMemoryAuthentication()
        .withUser("almahdi").password("1234").roles("USER","ADMIN")
        .and()
        .withUser("sef").password("123").roles("USER");
     /*
        PasswordEncoder passwordEncoder=passwordEncoder();
        String PWD=passwordEncoder.encode("123");
        System.out.println("Abdo**********\n"+PWD);
        auth.inMemoryAuthentication()
        .withUser("almahdi").password(passwordEncoder.encode("1234")).roles("USER","ADMIN")
        .and()
        .withUser("sef").password(PWD).roles("USER");
      /* 

        /* JBCD auth */

        /* 
        PasswordEncoder passwordEncoder=passwordEncoder();
        auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("select username as principal,password as credentials, active from users where username=?")
        .authoritiesByUsernameQuery("select username as principal,role as role from users_roles where username=?")
        .rolePrefix("ROLE_")
        .passwordEncoder(passwordEncoder);
        */

        /*user detailes service */
  
        auth.userDetailsService(userDetailsService);
       
    
    
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       
        //http.formLogin().loginPage("/nomPg");
        http.formLogin();
        http.authorizeHttpRequests().antMatchers("/").permitAll();
        //  http.authorizeHttpRequests().antMatchers("/delete/**","/edit/**","/save/**","/formPatients/**").hasRole("ADMIN");
        //  http.authorizeHttpRequests().antMatchers("/index/**").hasRole("USER");
         http.authorizeHttpRequests().antMatchers("/webjars/**");
         http.authorizeHttpRequests().antMatchers("/delete/**","/edit/**","/save/**","/formPatients/**").hasAnyAuthority("ADMIN");
         http.authorizeHttpRequests().antMatchers("/index/**").hasAnyAuthority("USER");
        // http.authorizeHttpRequests().antMatchers("/admin/**").hasRole("ADMIN");
        // http.authorizeHttpRequests().antMatchers("/user/**").hasRole("USER");
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");
    }
/*supprimer  mettre en application
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    */
}