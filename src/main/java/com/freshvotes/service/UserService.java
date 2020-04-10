package com.freshvotes.service;

import com.freshvotes.domain.User;
import com.freshvotes.repositories.UserRepository;
import com.freshvotes.security.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // We need this function to seperate the Service and Contriller.
    public User save (User user) {

        // set password
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // set authority
        Authority authority = new Authority();
        authority.setAuthority("ROLE_USER");
        authority.setUser(user);  // assign user to authority

        user.getAuthorities().add(authority);  // assign authority to user

        user = userRepo.save(user);  // wizard stuff from JpaRepository
        return user;
    }


}
