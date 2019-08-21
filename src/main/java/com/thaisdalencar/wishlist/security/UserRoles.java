package com.thaisdalencar.wishlist.security;

import org.springframework.security.core.GrantedAuthority;

public class UserRoles implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return "USER";
    }
}