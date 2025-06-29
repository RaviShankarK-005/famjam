package com.famjam.jwtUtil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.famjam.jwtUtil.userUtil.UserService;

@RestController
public class FirstController {
	
	@Autowired
	UserService userService;
	
	@Value("${famjam.env.test}")
	String envProp;
	
	@Value("${spring.profiles.active}")
	String actProf;
	
	@GetMapping(value="/env")
	public String getEnvProp() {
		return actProf+"_"+envProp;
	}

	@PostMapping("/home")
	public String home() {
		System.out.println(new BCryptPasswordEncoder().encode("moni123"));
		return "HOMEEEEE";
	}
}
