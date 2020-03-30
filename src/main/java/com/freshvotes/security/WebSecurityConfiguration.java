/*
2020-03-29 15:54:29
    This file set the configuration for the web service.
    By default, we need login to the website before we access any content on the website.
    We want to relax that security level.

    Basically, a Configuration class is a Java version of XML file.

    1. Difference between authentication and authorization (see notes)

 */

package com.freshvotes.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("luke3994")
            .password("asdfasdf")
            .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // only the homepage is allowed to all visitor without authentication
        // specify the login and logout page
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().hasRole("USER").and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .permitAll();

    }
}


