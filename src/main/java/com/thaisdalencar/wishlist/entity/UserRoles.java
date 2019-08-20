package com.thaisdalencar.wishlist.entity;

import org.springframework.security.core.GrantedAuthority;

public class UserRoles implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return "USER";
    }
}