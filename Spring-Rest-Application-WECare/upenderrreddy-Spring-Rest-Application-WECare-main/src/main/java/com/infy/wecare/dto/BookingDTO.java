package com.infy.wecare.dto;

import java.time.LocalDate;

import com.infy.wecare.entity.BookingEntity;

public class BookingDTO {
	int	bookingId;

	String userId;

	String coachId;

	LocalDate appointmentDate;
	 
	String slot;



	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCoachId() {
		return coachId;
	}

	public void setCoachId(String coachId) {
		this.coachId = coachId;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}
	
	public static BookingDTO prepareDTO(BookingEntity bookingEntity) {
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setAppointmentDate(bookingEntity.getAppointmentDate());
		bookingDTO.setBookingId(bookingEntity.getBookingId());
		bookingDTO.setCoachId(bookingEntity.getCoachId());
		bookingDTO.setSlot(bookingEntity.getSlot());
		bookingDTO.setUserId(bookingEntity.getUserId());
		return bookingDTO;
	}

	public static BookingEntity prepareEntity(BookingDTO bookingDTO) {
		BookingEntity bookingEntity = new BookingEntity();
		bookingEntity.setAppointmentDate(bookingDTO.getAppointmentDate());
		bookingEntity.setBookingId(bookingDTO.getBookingId());
		bookingEntity.setCoachId(bookingDTO.getCoachId());
		bookingEntity.setSlot(bookingDTO.getSlot());
		bookingEntity.setUserId(bookingDTO.getUserId());
		return bookingEntity;
	}

	@Override
	public String toString() {
		return "BookingDTO [bookingId=" + bookingId + ", userId=" + userId + ", coachId=" + coachId
				+ ", appointmentDate=" + appointmentDate + ", slot=" + slot + "]";
	}

}
