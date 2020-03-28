package com.couponsystem.CouponSystemSpring.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponsystem.CouponSystemSpring.beans.Login;

public interface LoginRepo extends JpaRepository<Login, Long> {
	
	Optional<Login> findBySubjectId (long subjectId);
	Optional<Login> findByToken (UUID token);
}
