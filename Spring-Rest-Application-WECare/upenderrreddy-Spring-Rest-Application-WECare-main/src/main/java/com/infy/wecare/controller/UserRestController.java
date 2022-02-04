package com.infy.wecare.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infy.wecare.dto.BookingDTO;
import com.infy.wecare.dto.LoginDTO;
import com.infy.wecare.dto.ResponseDTO;
import com.infy.wecare.dto.UserDTO;
import com.infy.wecare.exception.WecareException;
import com.infy.wecare.service.BookService;
import com.infy.wecare.service.UserService;


@RestController
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BookService bookService;
		
	@PostMapping("users")
	public ResponseEntity<ResponseDTO> createUser(@Valid @RequestBody UserDTO userDTO, Errors error){
		String userId = userService.createUser(userDTO);
		ResponseDTO response = new ResponseDTO();
		response.setMessage(userId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("users/login")
	public ResponseEntity<Boolean> loginUser(@Valid @RequestBody LoginDTO loginDTO) throws WecareException{
		Boolean response = userService.loginUser(loginDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("users/{userId}")
	public ResponseEntity<UserDTO> getUserProfile(@PathVariable String userId) throws WecareException{
		UserDTO userDTO = userService.getUserProfile(userId);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	@GetMapping("users/booking/{userId}")
	public List<BookingDTO> showMyAppointments(@PathVariable String userId){
		return bookService.findBookingByUserId(userId);
	}
}
