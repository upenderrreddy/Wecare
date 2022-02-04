package com.infy.wecare.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infy.wecare.dto.BookingDTO;
import com.infy.wecare.exception.WecareException;
import com.infy.wecare.service.BookService;

@RestController
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class BookRestController {
	
	@Autowired
	BookService bookService;
		
	@PostMapping("/users/{userId}/booking/{coachId}")
	public ResponseEntity<Boolean> bookAppointment(@PathVariable String userId,@PathVariable String coachId,@Valid @RequestBody BookingDTO bookingDTO ) throws WecareException{
		String slot = bookingDTO.getSlot();
		LocalDate dateOfAppointment = bookingDTO.getAppointmentDate();
		Boolean response = bookService.bookAppointment(userId, coachId, dateOfAppointment, slot);
		if(response.booleanValue())
			return new ResponseEntity<>(response, HttpStatus.OK);
		else
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	} 
	
	@PutMapping("/booking/{bookingId}")
	public ResponseEntity<Boolean> rescheduleAppointment(@PathVariable Integer bookingId,@Valid @RequestBody BookingDTO bookingDTO) throws WecareException{
		LocalDate dateOfAppointment = bookingDTO.getAppointmentDate();
		String slot = bookingDTO.getSlot();
		Boolean response = bookService.rescheduleAppointment(bookingId, dateOfAppointment, slot);
		if(response.booleanValue())
			return new ResponseEntity<>(response, HttpStatus.OK);
		else
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/booking/{bookingId}")
	public ResponseEntity<Boolean> cancelAppointment(@PathVariable Integer bookingId) throws WecareException{
		Boolean response = bookService.cancelAppointment(bookingId);
		return ResponseEntity.ok(response);
	}

}
