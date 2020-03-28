package com.couponsystem.CouponSystemSpring.rest;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.CouponSystemSpring.Help;
import com.couponsystem.CouponSystemSpring.beans.Login;
import com.couponsystem.CouponSystemSpring.dao.LoginDAO;
import com.couponsystem.CouponSystemSpring.dao.SystemDAO;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/login")
public class LoginController {

	@Autowired
	Help help;
	@Autowired
	LoginDAO loginDAO;
	@Autowired
	SystemDAO systemDAO;

	@PostMapping
	public ResponseEntity<?> addLogin(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, @RequestParam(name = "type") int type) {

		if (help.isValid(email, password, type)) {
			long subjectId = 0;
			switch (type) {

			case 1:
				subjectId = systemDAO.findCompanyByEmailOptional(email).get().getId();
				break;
			case 2:
				subjectId = systemDAO.findCustomerByEmailOptional(email).get().getId();
				break;
			default:
				break;

			}
			if (loginDAO.findBySubjectId(subjectId).isPresent()) {
				loginDAO.removeLogin(loginDAO.findBySubjectId(subjectId).get());
			}
			loginDAO.addLogin(new Login(subjectId, null, type, System.currentTimeMillis()));

			return new ResponseEntity<Optional<Login>>(loginDAO.findBySubjectId(subjectId), HttpStatus.OK);
		}
		return new ResponseEntity<String>("Access deny", HttpStatus.FORBIDDEN);
	}

	@DeleteMapping("/logout")
	public ResponseEntity<?> logOut(@RequestParam(name = "token") UUID token) {
		Optional<Login> login = loginDAO.findLoginByToken(token);
		if (login.isPresent()) {
			loginDAO.removeLogin(login.get());
			return new ResponseEntity<String>("Logged off", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Already logged off", HttpStatus.OK);
	}

}
