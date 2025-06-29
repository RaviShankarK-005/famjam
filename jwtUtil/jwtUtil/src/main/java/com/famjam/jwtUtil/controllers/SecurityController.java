package com.famjam.jwtUtil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.famjam.jwtUtil.config.JwtUtil;
import com.famjam.jwtUtil.dbUtil.User;
import com.famjam.jwtUtil.model.AuthRequest;
import com.famjam.jwtUtil.model.AuthResponse;
import com.famjam.jwtUtil.service.MyUserDetailsService;
import com.famjam.jwtUtil.userUtil.UserService;


@RestController
public class SecurityController {

	@Autowired
	UserService userService;
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;
    
    @Autowired
    private JwtUtil jwtUtil;
	
	@RequestMapping("/register")
	public String register(@RequestBody User user) {
		userService.register(user);
		return "Registered!!!";
	}
	
	 @PostMapping("/login")
	    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
	        try {
	            authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
	            );
	        } catch (BadCredentialsException e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	        }

	        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
	        final String jwt = jwtUtil.generateToken(userDetails);

	        return ResponseEntity.ok(new AuthResponse(jwt));
	    }
	
	
}
