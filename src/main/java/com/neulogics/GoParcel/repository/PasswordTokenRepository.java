package com.neulogics.GoParcel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neulogics.GoParcel.model.PasswordResetToken;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken,Long>{

	Optional<PasswordResetToken> findByToken(String token);
	
}
