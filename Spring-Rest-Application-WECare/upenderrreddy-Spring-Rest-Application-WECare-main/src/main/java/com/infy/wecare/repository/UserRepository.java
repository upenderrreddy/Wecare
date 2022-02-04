package com.infy.wecare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.wecare.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
	Optional<UserEntity> findByUserId(String userId);
}
