package com.famjam.jwtUtil.userUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.famjam.jwtUtil.dbUtil.User;
import com.famjam.jwtUtil.dbUtil.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDao userDao;

	public List<User> getUsers(){
		return userDao.getUsers();
	}

	public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
}
