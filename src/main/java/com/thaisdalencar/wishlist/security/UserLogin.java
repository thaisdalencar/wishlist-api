package com.thaisdalencar.wishlist.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class UserLogin implements UserDetails {

    private String user;
    private String password;

    public UserLogin() {
    }

    public UserLogin(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        var userRole = new UserRoles();
        return Arrays.asList(userRole);
    }

    public String getUsername(){
        return this.user;
    };

    public String getPassword() {
        return this.password;
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
