package com.freshvotes.service;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.not;


public class UserDetailsServiceTest {

    @Test
    public void generate_encrypted_password() {
        /*
        We should never store the password in plaintext.
        We use this testcase to add an password into the database.
         */
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "password123";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);

        assertThat(rawPassword, not(encodedPassword));

    }

}