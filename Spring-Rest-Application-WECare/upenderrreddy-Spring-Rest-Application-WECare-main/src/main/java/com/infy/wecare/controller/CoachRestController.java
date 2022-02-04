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
import com.infy.wecare.dto.CoachDTO;
import com.infy.wecare.dto.LoginDTO;
import com.infy.wecare.dto.ResponseDTO;
import com.infy.wecare.exception.WecareException;
import com.infy.wecare.service.BookService;
import com.infy.wecare.service.CoachService;

@RestController
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class CoachRestController {
	
	@Autowired
	CoachService coachService;
	
	@Autowired
	BookService bookService;
	
	@PostMapping("/coaches")
	public ResponseEntity<ResponseDTO> createCoach(@Valid @RequestBody CoachDTO coachDTO, Errors errors) {
		
		String coachId = coachService.createCoach(coachDTO);
		ResponseDTO response = new ResponseDTO();
		response.setMessage(coachId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/coaches/login")
	public ResponseEntity<Boolean> loginCoach(@Valid @RequestBody LoginDTO loginDTO) throws WecareException{
		Boolean response = coachService.loginCoach(loginDTO);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/coaches/{coachId}")
	public ResponseEntity<CoachDTO> getCoachProfile(@PathVariable String coachId) throws WecareException{
		CoachDTO coachDTO = coachService.getCoachProfile(coachId);
		return new ResponseEntity<>(coachDTO,HttpStatus.OK);
	}
	
	@GetMapping("/coaches/all")
	public List<CoachDTO> showAllCoaches(){
		return coachService.showAllCoaches();
	}
	
	@GetMapping("/coaches/booking/{coachId}")
	public List<BookingDTO> showMySchedule(@PathVariable String coachId){
		return bookService.findBookingByCoachId(coachId);
	}
}
