package com.infy.wecare.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infy.wecare.entity.BookingEntity;

@Repository
public interface BookRepository extends JpaRepository<BookingEntity, Integer> {
	Optional<BookingEntity> findByUserId(String userId);
	
	//This method is used to find list of bookings using given user id which are after current date.
	@Query(value = "select b from booking b where b.userId=?1 and b.appointmentDate>?2")
	List<BookingEntity> findBookingByUserId(String userId, LocalDate today);
	
	//This method is used to find list of bookings using given coach id which are after current date.
	@Query(value = "select b from booking b where b.coachId=?1 and b.appointmentDate>?2")
	List<BookingEntity> findBookingByCoachId(String coachId, LocalDate today);
	
	//This method is used to find booking details using given user id, date of appointment and slot.
	@Query(value = "select b from booking b where b.userId=?1 and b.appointmentDate=?2 and b.slot=?3")
	List<BookingEntity> findAllBookings(String userId, LocalDate appointmentDate, String slot);
}
