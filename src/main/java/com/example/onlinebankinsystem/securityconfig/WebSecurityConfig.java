package com.example.onlinebankinsystem.securityconfig;


import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.onlinebaknigsystem.dao.ClientRepository;
import com.example.onlinebaknigsystem.model.Client;


@EnableWebSecurity
public class WebSecurityConfig {

	private final ClientRepository clientRepo = null;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				authz -> authz.requestMatchers("/public/**").permitAll().anyRequest().authenticated())
				.formLogin(withDefaults());

		return http.build();
	}

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private UserDetailsService userDetailsService() {
		return userName -> {
			Client client = clientRepo.findByUsername(userName)
					.orElseThrow(() -> new UsernameNotFoundException("User not found"));
			return new User(client.getName(), client.getPassWord(),
					Collections.singletonList(new SimpleGrantedAuthority(userName)));
		};
	}

	private Customizer<FormLoginConfigurer<HttpSecurity>> withDefaults() {
		return null;
	}
}
