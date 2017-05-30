package com.pointwest.workforce.planner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.pointwest.workforce.planner.security.AuthProvider;
import com.pointwest.workforce.planner.security.TokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AuthProvider authProvider;
	
	@Bean
	public TokenFilter tokenFilter() throws Exception {
		return new TokenFilter();
	}
	
	public WebSecurityConfig() {
        super(true);
    }
	
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity
//	        .authorizeRequests()
//            .antMatchers( HttpMethod.GET, "/opportunities");
//            .addFilterBefore(new TokenFilter(), BasicAuthenticationFilter.class); 
//	            .antMatchers(
//                        HttpMethod.GET,
//                        "/",
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js"
//                ).permitAll();
//        httpSecurity.addFilter(tokenFilter());
    }
	
	/*@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {        
        auth.authenticationProvider(authProvider);        
    }*/
	
}