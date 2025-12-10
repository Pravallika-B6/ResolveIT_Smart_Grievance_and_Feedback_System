package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.Model.MyAppUserServices;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private MyAppUserServices appUserServices;

    @Bean
    public MyAppUserServices userDetailsService() {
        return appUserServices;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(appUserServices);

        authProvider.setPasswordEncoder(PasswordEncoder());
        return authProvider;
    }

    @Bean 
    public BCryptPasswordEncoder PasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity

                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(httpForm -> {
                    httpForm.loginPage("/login").permitAll();
                    
                    httpForm.defaultSuccessUrl("/complaint", true);
                })

                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/signup", "/css/**", "/js/**").permitAll();
                    registry.requestMatchers("/complaint").authenticated();
                    registry.anyRequest().authenticated();
                })

                .build();
    } 
}
