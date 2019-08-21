package com.thaisdalencar.wishlist.controller;


import com.thaisdalencar.wishlist.config.JwtTokenUtil;
import com.thaisdalencar.wishlist.config.UserLogin;
import com.thaisdalencar.wishlist.config.WebSecurityConfig;
import com.thaisdalencar.wishlist.controller.request.JwtRequest;
import com.thaisdalencar.wishlist.controller.response.JwtResponse;
import com.thaisdalencar.wishlist.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        if (webSecurityConfig.validateUser(authenticationRequest)) {
            return new JwtResponse(jwtTokenUtil.generateToken(new UserLogin()));
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
}
