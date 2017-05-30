package com.pointwest.workforce.planner.config;

public class RefConfig {}

//@SuppressWarnings("SpringJavaAutowiringInspection")
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Bean
//    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
//        return new JwtAuthenticationTokenFilter();
//	}
//	
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                // we don't need CSRF because our token is invulnerable
//                .csrf().disable()
//
//                //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//
//                // don't create session
//                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//
//                .authorizeRequests()
//                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//
//                // allow anonymous resource requests
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/",
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js"
//                ).permitAll()
//                .antMatchers("/auth/**").permitAll()
//                .anyRequest().authenticated();
//
//        // Custom JWT based security filter
//        httpSecurity
//                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//
//        // disable page caching
//        httpSecurity.headers().cacheControl();
//    }
//}