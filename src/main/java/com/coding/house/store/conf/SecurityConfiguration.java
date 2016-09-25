package com.coding.house.store.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.coding.house.store.dao.UserDAO;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/products/form").hasAnyRole("ADMIN")
								.antMatchers("/shoppingCart/**").permitAll()
								.antMatchers(HttpMethod.POST , "/products").hasRole("ADMIN")
								.antMatchers(HttpMethod.GET , "/products").hasRole("ADMIN")
								.antMatchers("/products/**").permitAll()
								.antMatchers("/resources/**").permitAll()
								.antMatchers("/").permitAll()
								.anyRequest().authenticated()
								.and().formLogin().loginPage("/login").permitAll()
								.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDAO).passwordEncoder(new BCryptPasswordEncoder());
	}
}
