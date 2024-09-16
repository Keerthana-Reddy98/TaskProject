package com.example.taskproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.taskproject.entity.Users;
import com.example.taskproject.payload.UserDto;
import com.example.taskproject.repository.UserRepository;
import com.example.taskproject.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		//userDto is not an entity of Users
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Users user = userDtotoEntity(userDto); //converted dto to Users
		Users savedUser = userRepository.save(user);
		return entitytoUserDto(savedUser);
	}
	
	private Users userDtotoEntity(UserDto userDto) {
		
		Users users = new Users();
		users.setName(userDto.getName());
		users.setEmail(userDto.getEmail());
		users.setPassword(userDto.getPassword());
		return users;
	}
	
	private UserDto entitytoUserDto(Users savedUser ) {
		UserDto userDto = new UserDto();
		userDto.setId(savedUser.getId());
		userDto.setName(savedUser.getName());
		userDto.setEmail(savedUser.getEmail());
		userDto.setPassword(savedUser.getPassword());
		 return userDto;
	}

}
