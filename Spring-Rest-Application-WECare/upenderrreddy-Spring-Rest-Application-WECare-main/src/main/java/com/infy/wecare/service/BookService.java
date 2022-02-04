package com.infy.wecare.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.infy.wecare.dto.BookingDTO;
import com.infy.wecare.entity.BookingEntity;
import com.infy.wecare.entity.CoachEntity;
import com.infy.wecare.entity.UserEntity;
import com.infy.wecare.exception.ExceptionConstants;
import com.infy.wecare.exception.WecareException;
import com.infy.wecare.repository.BookRepository;
import com.infy.wecare.repository.CoachRepository;
import com.infy.wecare.repository.UserRepository;
import com.infy.wecare.utitity.MailUtility;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	MailUtility mailUtility;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CoachRepository coachRepository;
	
	@Autowired
	Environment environment;
	
	
	public Boolean bookAppointment(String userId, String coachId, LocalDate appointmentDate, String slot) throws WecareException {
		/*
		 * checking for valid user and coach
		 */
		Optional<UserEntity> user = userRepository.findById(userId);
		if(user.isEmpty())
			throw new WecareException(environment.getProperty("user.not.found"));
		
		Optional<CoachEntity> coach = coachRepository.findByCoachId(coachId);
		if(coach.isEmpty())
			throw new WecareException(environment.getProperty("coach.not.found"));
		
		List<BookingEntity> bookingEntityFromDB = bookRepository.findAllBookings(userId, appointmentDate, slot);

		if(!bookingEntityFromDB.isEmpty()) 
			throw new WecareException(ExceptionConstants.BOOKING_ALREADY_EXISTS.toString());
		else {
			BookingDTO bookingDTO = new BookingDTO();
			bookingDTO.setAppointmentDate(appointmentDate);
			bookingDTO.setCoachId(coachId);
			bookingDTO.setSlot(slot);
			bookingDTO.setUserId(userId);
						
			BookingEntity bookingEntity = BookingDTO.prepareEntity(bookingDTO);
			bookingEntity = bookRepository.saveAndFlush(bookingEntity);


			
			UserEntity userEntity = user.get();
			CoachEntity coachEntity = coach.get();
			
			String userName = userEntity.getName();
			String coachName = coachEntity.getName();
			String email = userEntity.getEmail();
			String bookingSlot = bookingDTO.getSlot();
			LocalDate appointmentDate1 = bookingDTO.getAppointmentDate();
			int bookingId = bookingEntity.getBookingId();
			mailUtility.sendSchedulingEmail(userName, coachName, email, bookingId , bookingSlot, appointmentDate1);
			
			return true;
		}
	}
	
	public Boolean rescheduleAppointment(Integer bookingId, LocalDate appointmentDate, String slot) throws WecareException{
		BookingDTO bookingDTO = findByBookingId(bookingId);
		if(bookingDTO==null)
			throw new WecareException(ExceptionConstants.BOOKING_DOESNOT_EXISTS.toString());
		if(bookingDTO.getAppointmentDate().equals(appointmentDate) && bookingDTO.getSlot().equals(slot))
			throw new WecareException(ExceptionConstants.BOOKING_ALREADY_EXISTS.toString());
		else {
			bookingDTO.setAppointmentDate(appointmentDate);
			bookingDTO.setSlot(slot);
			bookingDTO.setBookingId(bookingId);
			bookRepository.saveAndFlush(BookingDTO.prepareEntity(bookingDTO));
			
			Optional<UserEntity> user = userRepository.findById(bookingDTO.getUserId());
			if(user.isEmpty())
				throw new WecareException(environment.getProperty("user.not.found"));
			
			Optional<CoachEntity> coach = coachRepository.findByCoachId(bookingDTO.getCoachId());
			if(coach.isEmpty())
				throw new WecareException(environment.getProperty("coach.not.found"));
			
			UserEntity userEntity = user.get();
			CoachEntity coachEntity = coach.get();
			String userName = userEntity.getName();
			String coachName = coachEntity.getName();
			String email = userEntity.getEmail();
			String bookingSlot = bookingDTO.getSlot();
			mailUtility.sendReschedulingEmail(userName, coachName, email, bookingId, bookingSlot, appointmentDate);
			
			return true;
		}
		
	}
	
	/*
	 * returns false if there is no booking with given bookingId
	 * returns true if successfully deleted
	 */
	public Boolean cancelAppointment(Integer bookingId) throws WecareException {
		BookingDTO bookingDTO = findByBookingId(bookingId);
		if(bookingDTO==null)
			throw new WecareException(ExceptionConstants.BOOKING_DOESNOT_EXISTS.toString());
		bookRepository.deleteById(bookingId);
		Optional<UserEntity> user = userRepository.findById(bookingDTO.getUserId());
		if(user.isEmpty())
			throw new WecareException(environment.getProperty("user.not.found"));
		
		Optional<CoachEntity> coach = coachRepository.findByCoachId(bookingDTO.getCoachId());
		if(coach.isEmpty())
			throw new WecareException(environment.getProperty("coach.not.found"));
		
		UserEntity userEntity = user.get();
		CoachEntity coachEntity = coach.get();
		
		String userName = userEntity.getName();
		String coachName = coachEntity.getName();
		String email = userEntity.getEmail();
		String bookingSlot = bookingDTO.getSlot();
		LocalDate appointmentDate = bookingDTO.getAppointmentDate();
		mailUtility.sendCancellingEmail(userName, coachName, email, bookingId, bookingSlot, appointmentDate);
		return true;
	}
	
	
	public BookingDTO findByBookingId(Integer bookingId){
		Optional<BookingEntity> bookingEntity = bookRepository.findById(bookingId);
		if(bookingEntity.isPresent())
			return BookingDTO.prepareDTO(bookingEntity.get());
		return null;
	}
	
	public List<BookingDTO> findBookingByUserId(String userId){
		List<BookingEntity> bookingEntities = bookRepository.findBookingByUserId(userId, LocalDate.now());
		List<BookingDTO> bookingDTOs = new ArrayList<>();
		for(BookingEntity bookingEntity:bookingEntities)
			bookingDTOs.add(BookingDTO.prepareDTO(bookingEntity));
		return bookingDTOs;
	}
	
	public List<BookingDTO> findBookingByCoachId(String coachId){
		List<BookingEntity> bookingEntities = bookRepository.findBookingByCoachId(coachId, LocalDate.now());
		List<BookingDTO> bookingDTOs = new ArrayList<>();
		for(BookingEntity bookingEntity:bookingEntities)
			bookingDTOs.add(BookingDTO.prepareDTO(bookingEntity));
		return bookingDTOs;
	}
}
