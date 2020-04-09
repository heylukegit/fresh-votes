package com.freshvotes.service;

import com.freshvotes.domain.User;
import com.freshvotes.repositories.UserRepository;
import com.freshvotes.security.CustomSecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service  /* Always remember to annotate your class properly */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    /*
    This method should access the database and look for the users' information.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /* Search user name in the repository/database */
        User user = userRepo.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("Invalid Username or Password");

        return new CustomSecurityUser(user);
    }
}
