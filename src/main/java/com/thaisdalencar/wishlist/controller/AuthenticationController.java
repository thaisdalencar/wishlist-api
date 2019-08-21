package com.thaisdalencar.wishlist.controller;


import com.thaisdalencar.wishlist.security.jwt.JwtTokenUtil;
import com.thaisdalencar.wishlist.security.WebSecurityConfig;
import com.thaisdalencar.wishlist.controller.request.JwtRequest;
import com.thaisdalencar.wishlist.controller.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        if (webSecurityConfig.validateUser(authenticationRequest)) {
            return new JwtResponse(jwtTokenUtil.generateToken(authenticationRequest));
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
}
