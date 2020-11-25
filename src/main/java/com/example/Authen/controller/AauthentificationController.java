package com.example.Authen.controller;


import com.example.Authen.dao.UserDao;
import com.example.Authen.entity.User;
import com.example.Authen.request.JwtAuthenticationResponse;
import com.example.Authen.request.LoginRequest;

import com.example.Authen.security.JwtTokenProvider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author Mohamed AIT BOUAAZA
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")



public class AauthentificationController {


    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    UserDao userDao;
    public static Logger LOG = Logger.getLogger("ApiAuthentification");

    // api de l'authentification
    @PostMapping("/signin")
    ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User userConnected = userDao.findByLogin(loginRequest.getUsername());
        String jwt = tokenProvider.generateToken(authentication);
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.set("GeneratedToken", jwt);
        LOG.info("Connecté");
        LOG.info("Utilisateur ID : " + userConnected.getIdUtilisateur());
        return new ResponseEntity<>(new JwtAuthenticationResponse(jwt), httpHeader, HttpStatus.OK);

    }


    // Api déconnexion
    @GetMapping("/logout")
    public ResponseEntity<Object> getLogoutPage(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Disconnected");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
            new UsernamePasswordAuthenticationToken(null, null);
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.set("GeneratedToken", null);
        return new ResponseEntity<>(new JwtAuthenticationResponse(null), httpHeader, HttpStatus.OK);
    }
}
