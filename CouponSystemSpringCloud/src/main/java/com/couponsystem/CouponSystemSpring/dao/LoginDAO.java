package com.couponsystem.CouponSystemSpring.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.couponsystem.CouponSystemSpring.beans.Login;
import com.couponsystem.CouponSystemSpring.repo.CompanyRepo;
import com.couponsystem.CouponSystemSpring.repo.CustomerRepo;
import com.couponsystem.CouponSystemSpring.repo.LoginRepo;

@Repository
public class LoginDAO {

	@Autowired
	CompanyRepo companyRepo;
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	LoginRepo loginRepo;
	@Autowired
	SystemDAO systemDAO;

	public void addLogin(Login login) {
		loginRepo.save(login);

	}

	public void removeLogin(Login login) {
		loginRepo.delete(login);
	}

	public Collection<Login> getAllLogins() {
		return loginRepo.findAll();

	}
	
	public Optional<Login> findBySubjectId(long subjectId) {
		return loginRepo.findBySubjectId(subjectId);
		
	}
	
	public Optional<Login> findLoginByToken(UUID token) {
		return loginRepo.findByToken(token);
	}
}
