package com.infy.wecare.dto;

import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.infy.wecare.entity.UserEntity;

public class UserDTO {
	
	String userId;
	@NotNull
	@Length(min = 5, max = 10,message = "minimum length should be 5 and maximum length should be 10.")
	String password; //Cannot be null, minimum length should be 5 and maximum length should be 10.
	@NotNull
	@Length(min = 3, max = 50,message = "minimum length should be 3 and maximum length should be 50.")
	String name; //Cannot be null, minimum length should be 3 and maximum length should be 50.

	LocalDate dateOfBirth;
	char gender;

	@Digits(integer = 10, fraction = 0,message = "length should be 10")
	@NotNull(message = "phone number cannot be empty")
	long mobileNumber; // Cannot be null, minimum length should be 10 and maximum length should be 10.

	@Email
	String email; // Should be a valid email.

	@Digits(integer = 6, fraction = 0,message = "length should be 6")
	@NotNull(message = "pincode cannot be empty")
	int	pincode; // Cannot be null, minimum length should be 6 and maximum length should be 6.

	@NotNull
	@Length(min = 3, max = 20,message = "minimum length should be 3 and maximum length should be 20.")
	String city; // Cannot be null, minimum length should be 3 and maximum length should be 20.
	
	@NotNull
	@Length(min = 3, max = 20,message = "minimum length should be 3 and maximum length should be 20.")
	String state; // Cannot be null, minimum length should be 3 and maximum length should be 20.

	@NotNull
	@Length(min = 3, max = 20,message = "minimum length should be 3 and maximum length should be 20.")
	String country; // Cannot be null, minimum length should be 3 and maximum length should be 20.

	



	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getPincode() {
		return pincode;
	}


	public void setPincode(int pincode) {
		this.pincode = pincode;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public static UserEntity prepareEntity(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		userEntity.setCity(userDTO.getCity());
		userEntity.setCountry(userDTO.getCountry());
		userEntity.setDateOfBirth(userDTO.getDateOfBirth());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setGender(userDTO.getGender());
		userEntity.setMobileNumber(userDTO.getMobileNumber());
		userEntity.setName(userDTO.getName());
		userEntity.setPassword(userDTO.getPassword());
		userEntity.setPincode(userDTO.getPincode());
		userEntity.setState(userDTO.getState());
		userEntity.setUserId(userDTO.getUserId());
		return userEntity;
	}


	public static UserDTO prepareDTO(UserEntity userEntity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setCity(userEntity.getCity());
		userDTO.setCountry(userEntity.getCountry());
		userDTO.setDateOfBirth(userEntity.getDateOfBirth());
		userDTO.setEmail(userEntity.getEmail());
		userDTO.setGender(userEntity.getGender());
		userDTO.setMobileNumber(userEntity.getMobileNumber());
		userDTO.setName(userEntity.getName());
		userDTO.setPassword(userEntity.getPassword());
		userDTO.setPincode(userEntity.getPincode());
		userDTO.setState(userEntity.getState());
		userDTO.setUserId(userEntity.getUserId());
		return userDTO;
	}
}
