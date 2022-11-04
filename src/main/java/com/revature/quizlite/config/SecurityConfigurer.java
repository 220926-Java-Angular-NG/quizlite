package com.revature.quizlite.config;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This class is a single source of our security configuration
 * What endpoints should be restricted, which shouldn't restricted
 * By default, all endpoints are restricted
 *   We will need to permit certain endpoints to be ignored by the auth module
 *      /login      -> we need to be able to tell the server who we are
 *      /register   -> we need to be able to create new users without requiring them to have auth
 *  We need to define a way to permit people to access the endpoints that require auth
 *      We are going to use JWTs to prove that someone is authenticated
 *          The JWT will be passed with each request made from the front end
 *          Extract the JWT from EACH REQUEST and determine if its valid
 *              if valid -> let them proceed
 *              if not -> return a 401 Unauthorized
 *      A JWT filter bean that we then will run before every request that comes in
 *
 */
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
}
