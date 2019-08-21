package com.thaisdalencar.wishlist.service;

import com.thaisdalencar.wishlist.config.UserLogin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService {

    public UserDetails loadUserByUsername(String name) {
        return new UserLogin();
    }
}
