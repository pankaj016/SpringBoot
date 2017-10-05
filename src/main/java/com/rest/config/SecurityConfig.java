package com.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private static final String ADMIN = "ADMIN";
	private static final String USER = "USER";
	private static String REALM="MY_TEST_REALM";
	//configure roles

	
	@Autowired
	 public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
	     auth.inMemoryAuthentication().withUser("pankaj").password("pankaj").roles(USER)
			.and().withUser("user01").password("user01password").roles(USER)
			.and().withUser("admin01").password("admin01password").roles(USER,ADMIN)
			.and().withUser("tiwari").password("tiwari").roles(USER,ADMIN);
	 }  
	     
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	  
	      http.csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/soundex/**").hasRole("USER")
	        .antMatchers("/product/**").hasRole("USER")
	        .antMatchers("/product/**").hasRole("USER")
	        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
	        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
	    }
	     
	    @Bean
	    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
	        return new CustomBasicAuthenticationEntryPoint();
	    }
	     
	    /* To allow Pre-flight [OPTIONS] request from browser */
	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	    }

	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
	
}
