package com.infy.wecare.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "booking")
public class BookingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer	bookingId;
	String	userId;
	String	coachId;
	String	slot;
	LocalDate appointmentDate;
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
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
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	@Override
	public String toString() {
		return "BookingEntity [bookingId=" + bookingId + ", userId=" + userId + ", coachId=" + coachId + ", slot="
				+ slot + ", appointmentDate=" + appointmentDate + "]";
	}
	
}
