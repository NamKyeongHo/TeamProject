package com.example.pet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers("/css/**", "/js/**", "/image/**").permitAll()
                        .requestMatchers("/","/item/**").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/manageCustomer/**").hasRole("ADMIN")
                        .requestMatchers("/manageCategory/**").hasRole("ADMIN")
                        .requestMatchers("/addProduct/**").hasRole("ADMIN")
                        .requestMatchers("/addProductAdd/**").hasRole("ADMIN")
                        .requestMatchers("/manageOrder/**").hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())



                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/**")))

                .formLogin((formLogin) -> formLogin
                        .loginPage("/login.html")
                        .loginProcessingUrl("/login_proc")
                        .defaultSuccessUrl("/", true)
                        .usernameParameter("id")
                        .failureUrl("/login/error")
                        .permitAll())

                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true));

                return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
