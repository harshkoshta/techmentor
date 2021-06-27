package com.techmentor.Security;

import com.techmentor.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).invalidSessionUrl("/login").and()
				.csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/certificates","/certificate","/certificate/*", "/resetpassword", "/resetpassword/*",
						"/", "/resources/**","/assets/**", "/course", "/register", "/coursedetail/*", "/search/certificate")
				.permitAll().antMatchers("/pay", "/payment").permitAll()
				.antMatchers(HttpMethod.POST, "/register", "/resetpassword", "/resetpassword/*","/subscribeemail").permitAll()
				.antMatchers().hasAuthority("ADMIN")
				.antMatchers("/createcourse", "/createcourse/**","/authorize","/authority/sendmailtosubscriber","/updatecourse", "/updatecourse/**", "/deletecourse", "/deletecourse/**")
				.hasAnyAuthority("ADMIN", "MANAGER").anyRequest().authenticated().and().formLogin().loginPage("/login")
				.permitAll().defaultSuccessUrl("/", true)

				.and().logout().deleteCookies("JSESSIONID")

				.and().rememberMe().key("techmentorharshkoshta").tokenValiditySeconds(86400);
	}

}
