package com.nit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig 
{
	 @Bean
	    public UserDetailsService userDetailsService() {
	        UserDetails user = User.builder()
	            .username("user")
	            .password(passwordEncoder().encode("kohli"))
	            .roles("USER")
	            .build();

	        UserDetails admin = User.builder()
	            .username("admin")
	            .password(passwordEncoder().encode("rohit"))
	            .roles("ADMIN")
	            .build();

	        return new InMemoryUserDetailsManager(user, admin);
	    }

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	                .authorizeRequests()  
	                .requestMatchers("/admin/**").hasRole("ADMIN")
	                .requestMatchers("/user/**").hasRole("USER")
	                .requestMatchers("/", "/home").permitAll()
	                .anyRequest().authenticated()
	                .and()
	            .httpBasic()
	                .and()
	            .csrf().disable(); // For simplicity, CSRF is disabled. Enable in production!

	        return http.build();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
}