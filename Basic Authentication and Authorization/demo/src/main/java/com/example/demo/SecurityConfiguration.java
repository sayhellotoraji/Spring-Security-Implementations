package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Add annotation
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Set your configuration on the auth object
		
		auth.inMemoryAuthentication()
				.withUser("user1")
				.password("1234")
				.roles("USER")
				.and()
				.withUser("user2")
				.password("1234")
				.roles("ADMIN");
				
		
	}
	

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/user").hasAnyRole("USER","ADMIN")
				.antMatchers("/admin").hasRole("ADMIN")
				.antMatchers("/").permitAll()
				.and().formLogin();
	}


}
