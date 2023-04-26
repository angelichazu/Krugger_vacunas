package com.krugger.vacunas.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/empleado/crearEmpleado", "/empleado/listarEmpleados", "/empleado/filtrarEmpleados").hasRole("ADMIN")
                .antMatchers("/empleado/actualizarEmpleado/**", "/empleado/infoEmpleado/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/auth/login").permitAll()
                .antMatchers( "/api/vacuna/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
