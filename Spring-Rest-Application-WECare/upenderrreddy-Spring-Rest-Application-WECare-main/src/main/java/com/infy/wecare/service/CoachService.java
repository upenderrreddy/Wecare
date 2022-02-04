package com.infy.wecare.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.infy.wecare.dto.CoachDTO;
import com.infy.wecare.dto.LoginDTO;
import com.infy.wecare.entity.CoachEntity;
import com.infy.wecare.exception.ExceptionConstants;
import com.infy.wecare.exception.WecareException;
import com.infy.wecare.repository.CoachRepository;

@Service
public class CoachService {
	
	@Autowired
	Environment environment;
	
	@Autowired
	CoachRepository coachRepository;
	public String createCoach(CoachDTO coachDTO) {
		CoachEntity coachEntity = coachRepository.saveAndFlush(CoachDTO.prepareEntity(coachDTO));
		return coachEntity.getCoachId();
	}
	
	public Boolean loginCoach(LoginDTO loginDTO) throws WecareException {
		Optional<CoachEntity> coach = coachRepository.findById(loginDTO.getUserId());
		if(coach.isPresent()) {
			return loginDTO.getPassword().equals(coach.get().getPassword());
			
		}
		else
			throw new WecareException(ExceptionConstants.COACH_NOT_FOUND.toString());
		
	}
	
	public CoachDTO getCoachProfile(String coachId) throws WecareException {
		Optional<CoachEntity> coachEntity = coachRepository.findById(coachId);
		if(coachEntity.isPresent()) {
			CoachDTO coachDTO = CoachDTO.prepareDTO(coachEntity.get());
			coachDTO.setPassword("****");
			return coachDTO;
		}
		else 
			throw new WecareException(environment.getProperty("coach.not.found"));
	}
	
	public List<CoachDTO> showAllCoaches(){
		List<CoachEntity> coachEntities = coachRepository.findAll();
		List<CoachDTO> coachDTOs = new ArrayList<>();
		for(CoachEntity coachEntity : coachEntities)
			coachDTOs.add(CoachDTO.prepareDTO(coachEntity));
		return coachDTOs;
	}
}
