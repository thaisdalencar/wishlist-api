package com.thaisdalencar.wishlist.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class UserLogin implements UserDetails {

    public Collection<? extends GrantedAuthority> getAuthorities() {
        var userRole = new UserRoles();
        return Arrays.asList(userRole);
    }

    public String getPassword() {
        return "abc";
    };

    public String getUsername(){
        return "abc";
    };

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked(){
        return true;
    };

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
