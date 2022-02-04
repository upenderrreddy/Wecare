package com.infy.wecare.dto;

import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.infy.wecare.entity.CoachEntity;


public class CoachDTO {
	String coachId;
	@NotNull(message = "Cannot be null")
	@Length(min = 5, max = 10,message = "minimum length should be 5 and maximum length should be 10.")
	String password;//Cannot be null, minimum length should be 5 and maximum length should be 10.

	
	@NotNull(message = "Cannot be null")
	@Length(min = 3, max = 50,message = "minimum length should be 3 and maximum length should be 50.")
	String name; // Cannot be null, minimum length should be 3 and maximum length should be 50.

	LocalDate dateOfBirth;

	 
	char gender;

	@Digits(integer = 10, fraction = 0,message = "length should be 10")
	@NotNull(message = "phone number cannot be empty")
	Long mobileNumber; //Cannot be null, minimum length should be 10 and maximum length should be 10.

	@NotNull(message = "Cannot be null")
	@Length(min = 3, max = 50,message = "minimum length should be 3 and maximum length should be 50.")
	String speciality;

	public String getCoachId() {
		return coachId;
	}

	public void setCoachId(String coachId) {
		this.coachId = coachId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public static CoachEntity prepareEntity(CoachDTO coachDTO) {
		CoachEntity coachEntity = new CoachEntity();
		coachEntity.setCoachId(coachDTO.getCoachId());
		coachEntity.setDateOfBirth(coachDTO.getDateOfBirth());
		coachEntity.setGender(coachDTO.getGender());
		coachEntity.setMobileNumber(coachDTO.getMobileNumber());
		coachEntity.setName(coachDTO.getName());
		coachEntity.setPassword(coachDTO.getPassword());
		coachEntity.setSpeciality(coachDTO.getSpeciality());
		return coachEntity;
	}

	public static CoachDTO prepareDTO(CoachEntity coachEntity) {
		CoachDTO coachDTO = new CoachDTO();
		coachDTO.setCoachId(coachEntity.getCoachId());
		coachDTO.setDateOfBirth(coachEntity.getDateOfBirth());
		coachDTO.setGender(coachEntity.getGender());
		coachDTO.setMobileNumber(coachEntity.getMobileNumber());
		coachDTO.setName(coachEntity.getName());
		coachDTO.setPassword(coachEntity.getPassword());
		coachDTO.setSpeciality(coachEntity.getSpeciality());
		return coachDTO;
	}
	
}
