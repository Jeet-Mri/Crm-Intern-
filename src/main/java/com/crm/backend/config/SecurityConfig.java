package com.crm.backend.config;

import com.crm.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonConfig commonconfig;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(commonconfig.getEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests(authorize-> authorize
                        .requestMatchers("/user/Login/**").permitAll()
                        .requestMatchers("/user/createAdmin/**").hasRole("ADMIN")
                        .requestMatchers("/user/filter/**").hasAnyAuthority("ADMIN","STUDENT")
                        .requestMatchers("/txn/create/**").hasAuthority("ADMIN")
                        .requestMatchers("/txn/return/**").hasAuthority("ADMIN")
                        .requestMatchers("/book/addBook/**").hasAuthority("ADMIN")
                        .requestMatchers("/book/filter/**").hasAnyAuthority("ADMIN","STUDENT")
                        .anyRequest().authenticated()
                ).formLogin(withDefaults()).httpBasic(withDefaults()).csrf(csrf->csrf.disable());
        return http.build();
    }

}
