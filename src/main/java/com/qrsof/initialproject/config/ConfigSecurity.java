package com.qrsof.initialproject.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecurity {
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails gustavo = User.builder()
                .username("gustavo")
                .password("{noop}gustavo123")
                .roles("Empleado")
                .build();
        UserDetails javier = User.builder()
                .username("javier")
                .password("{noop}javier123")
                .roles("Empleado", "Jefe")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin123")
                .roles("Jefe")
                .build();


        return new InMemoryUserDetailsManager(gustavo, javier, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configure -> {
            configure.requestMatchers(HttpMethod.GET, "/libros/").hasRole("Empleado")
                    .requestMatchers(HttpMethod.GET, "/libros/**").hasRole("Empleado")
                    .requestMatchers(HttpMethod.POST, "/libros/crear").hasRole("Jefe")
                    .requestMatchers(HttpMethod.PUT, "/libros/actualizar/**").hasRole("Jefe")
                    .requestMatchers(HttpMethod.DELETE, "/libros/**").hasRole("Jefe");
        });
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();

    }

}
