/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harris.popcorn.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                    .authorizeRequests()
                .antMatchers("/form/**").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/users").hasRole("USER")
                .and ()
                .formLogin(
                        form ->form
                            .loginPage("/login")
                            .loginProcessingUrl("/login")
                            .defaultSuccessUrl("/movie/movies")
                            .permitAll()
                 
                
                )
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()

                
                );
                
                
                
                
                
                
//                .authorizeHttpRequests((requests) -> requests
//				.antMatchers("/").permitAll()
//				.anyRequest().authenticated()
//			)
//			.formLogin((form) -> form
//				.loginPage("/form")
//				.permitAll()
//			)
//			.logout((logout) -> logout.permitAll());
//                
//                
                
                
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("1234"))
//                .roles("ADMIN")
//                .build();
//        
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("5678"))
//                .roles("USER")
//                .build();
//        
//        return new InMemoryUserDetailsManager(admin , user);
//
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
