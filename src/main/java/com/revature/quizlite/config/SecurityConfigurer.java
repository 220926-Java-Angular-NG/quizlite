package com.revature.quizlite.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.quizlite.service.UserService;
import com.revature.quizlite.util.JWTRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class is a single source of our security configuration
 * What endpoints should be restricted, which shouldn't restricted
 * By default, all endpoints are restricted
 *   We will need to permit certain endpoints to be ignored by the auth module
 *      /login      -> we need to be able to tell the server who we are
 *      /register   -> we need to be able to create new users without requiring them to have auth
 *  We need to define a way to permit people to access the endpoints that require auth
 *      We are going to use JWTs to prove that someone is authenticated
 *          The JWT will be passed with each request made from the front end via Authorization
 *                  -> the format should be Authorization: Bearer <token>
 *          Extract the JWT from EACH REQUEST and determine if its valid
 *              if valid -> let them proceed
 *              if not -> return a 401 Unauthorized
 *      A JWT filter bean that we then will run before every request that comes in
 *
 */
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final JWTRequestFilter jwtRequestFilter;
    private final UserService userService;

    public SecurityConfigurer(@Lazy JWTRequestFilter jwtRequestFilter, @Lazy UserService userService) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    // TODO: bean for our password encoder, for our authentication manager, and for our object mapper

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
