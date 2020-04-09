package com.freshvotes.security;

import com.freshvotes.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomSecurityUser extends User implements UserDetails {

    private static final long serialVersionUID = -6017149916834463524L;

    // make a default empty constructor in case of all the complains from Spring
    public CustomSecurityUser() {}

    /*
    Pass all the data retrived from the database to this container.
     */
    public CustomSecurityUser(User user) {
        this.setAuthorities(user.getAuthorities());
        this.setId(user.getId());
        this.setName(user.getName());
        this.setPassword(user.getPassword());
        this.setUsername(user.getUsername());
    }

    @Override
    public Set<Authority> getAuthorities() {
        /*
        We need the "super" here. Because we are using the method from parent object.
        If we use "this" or nothing (by default still "this") here, we will jump into a
        infinite loop of set -> get -> get -> get ......
         */
        return super.getAuthorities();  // should be "super" here, not "this"
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    /*
    These methods are used to determine whether the user is active.
    todo the boolean is hard-coded.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
