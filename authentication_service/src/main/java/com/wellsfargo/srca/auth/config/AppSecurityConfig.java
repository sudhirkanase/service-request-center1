package com.wellsfargo.srca.auth.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.wellsfargo.srca.services.AppUserDetailsService;

/**
 * 
 * Application security configuration goes in this file
 *
 */

@Configuration
@EnableRedisHttpSession
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AppUserDetailsService userDetailservice;

	@Autowired
	UnAuthorizedAuthenticationEntryPoint unAuthoEntryPoint;

//	@Autowired
//	AuthenticationFilter authFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.userDetailsService(userDetailservice);
		auth.authenticationProvider(authProvider());
		// auth.inMemoryAuthentication();
		// auth.ldapAuthentication();
		// auth.jdbcAuthentication();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().
				//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				//.and().
				exceptionHandling()
				.authenticationEntryPoint(unAuthoEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
				.authorizeRequests()
				.antMatchers("/authenticate").permitAll()
				// .antMatchers("/admin/**").hasRole("ADMIN")
				// .antMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated();
		
				// .and()
				// .httpBasic();
				// .formLogin();
				// .formLogin().loginPage("/login").permitAll()
				// .defaultSuccessUrl("/", true)
				// .passwordParameter("password")
				// .usernameParameter("username");
		
				// http.csrf().

		//http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder getpassPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailservice);
		authProvider.setPasswordEncoder(getpassPasswordEncoder());
		return authProvider;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		//webSecurity.ignoring().antMatchers(HttpMethod.POST, "/authenticate").
		webSecurity.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").and()
		.ignoring().antMatchers(HttpMethod.GET, "/" // Other Stuff You want to Ignore
		).and().ignoring().antMatchers("/h2-console/**/**");// Should not be in Production!
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public HttpSessionIdResolver httpSessionIdResolver() {
	    return HeaderHttpSessionIdResolver.xAuthToken(); 
	}
}
