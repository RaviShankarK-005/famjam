package com.famjam.jwtUtil.userUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.famjam.jwtUtil.dbUtil.User;
import com.famjam.jwtUtil.dbUtil.UserRepository;

@Repository
public class UserDao {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> getUsers(){
		return userRepository.findAll();
	}
}
