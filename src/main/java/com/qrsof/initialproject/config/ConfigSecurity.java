package com.qrsof.initialproject.config;


import com.qrsof.initialproject.filter.JwtReqFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class ConfigSecurity {

    @Autowired
    @Lazy
    //Evita que se esten llamando las clases
    private JwtReqFilter jwtReqFilter;

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        UserDetails gustavo = User.builder()
//                .username("gustavo")
//                .password("{noop}gustavo123")
//                .roles("EMPLEADO")
//                .build();
//        UserDetails javier = User.builder()
//                .username("javier")
//                .password("{noop}javier123")
//                .roles("EMPLEADO", "JEFE")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}admin123")
//                .roles("JEFE")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(gustavo, javier, admin);
//    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
     return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configure -> {
            configure.requestMatchers(HttpMethod.GET, "/libros/").hasRole("EMPLEADO")
                    .requestMatchers(HttpMethod.GET, "/libros/**").hasRole("EMPLEADO")
                    .requestMatchers(HttpMethod.POST, "/libros/crear").hasRole("JEFE")
                    .requestMatchers(HttpMethod.PUT, "/libros/actualizar/**").hasRole("JEFE")
                    .requestMatchers(HttpMethod.DELETE, "/libros/**").hasRole("JEFE")
                    .requestMatchers(HttpMethod.GET, "/v1/categorias").hasRole("EMPLEADO")
                    .requestMatchers(HttpMethod.GET, "/v1/categorias/**").hasRole("EMPLEADO")
                    .requestMatchers(HttpMethod.POST, "/v1/categorias").hasRole("JEFE")
                    .requestMatchers(HttpMethod.PUT, "/v1/categorias/**").hasRole("JEFE")
                    .requestMatchers(HttpMethod.DELETE, "/v1/categorias/**").hasRole("JEFE")
                    .requestMatchers("/v1/authenticate", "/v3/api-docs/**","/swagger-ui/**", "/swagger-ui.html").permitAll();
        })
                        .addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class)
                                .sessionManagement(session -> session
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();

    }

}
