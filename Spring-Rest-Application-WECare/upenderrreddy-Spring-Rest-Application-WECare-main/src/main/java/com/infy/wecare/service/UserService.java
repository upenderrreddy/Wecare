package com.infy.wecare.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.infy.wecare.dto.LoginDTO;
import com.infy.wecare.dto.UserDTO;
import com.infy.wecare.entity.UserEntity;
import com.infy.wecare.exception.ExceptionConstants;
import com.infy.wecare.exception.WecareException;
import com.infy.wecare.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Environment environment;
	
	public String createUser(@Valid @RequestBody UserDTO userDTO){
		UserEntity userEntity = userRepository.saveAndFlush(UserDTO.prepareEntity(userDTO));
		return userEntity.getUserId();
	}
	
	public Boolean loginUser(@Valid @RequestBody LoginDTO loginDTO) throws WecareException{
		Optional<UserEntity> userEntity = userRepository.findById(loginDTO.getUserId());
		if(userEntity.isPresent()) 
			return userEntity.get().getPassword().equals(loginDTO.getPassword());
		else
			throw new WecareException(ExceptionConstants.USER_NOT_FOUND.toString());
			
	}

	public UserDTO getUserProfile(String userId) throws WecareException{
		Optional<UserEntity> userEntity = userRepository.findById(userId);
		if(userEntity.isPresent()) {
			UserDTO userDTO = UserDTO.prepareDTO(userEntity.get());
			userDTO.setPassword("****");
			return userDTO;
		}
		else
			throw new WecareException(environment.getProperty("user.not.found"));
	}
}
