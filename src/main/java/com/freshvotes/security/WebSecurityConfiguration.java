/*
2020-03-29 15:54:29
    This file set the configuration for the web service.
    By default, we need login to the website before we access any content on the website.
    We want to relax that security level.

    Basically, a Configuration class is a Java version of XML file.

    1. Difference between authentication and authorization (see notes)


2020-03-30 16:21:10
    1. add password encoder

 */

package com.freshvotes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /*
    We add a bean here so that this method can be managed by Spring automatically.
    If we want to use this method in other files, we can just add
    @Autowired and then use this method
    */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    Put all the traditional configurations in a method.
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .passwordEncoder(getPasswordEncoder())
            .withUser("luke")
            .password(getPasswordEncoder().encode("asdf"))
            .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*
        Only the homepage is allowed to all visitor without authentication.
        Specify the login and logout page.
         */
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/").permitAll()
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


