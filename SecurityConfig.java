package com.example.isa.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MyUserDetailsService userDetailsService;

    public SecurityConfig(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/students/**").hasAnyRole("STUDENT","PROFESOR")
                        .requestMatchers("/profesors/**").hasRole("PROFESOR")
                        .requestMatchers("/predmeti/**").hasAnyRole("STUDENT","PROFESOR")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/index", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")          //  pokreće odjavu
                        .logoutSuccessUrl("/login")    // gde da ode nakon odjave
                        .invalidateHttpSession(true)   //  briše sesiju
                        .deleteCookies("JSESSIONID")   // briše cookie
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .maximumSessions(1)            //  samo jedna sesija po korisniku
                        .maxSessionsPreventsLogin(false)
                )
                .sessionManagement(session -> session
                        .sessionFixation().migrateSession() //  zaštita od session hijacking
                        .invalidSessionUrl("/login?invalid") //  ako sesija istekne
                );

        return http.build();
    }
}
