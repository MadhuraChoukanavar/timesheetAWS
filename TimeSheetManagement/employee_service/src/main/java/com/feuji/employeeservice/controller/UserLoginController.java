package com.feuji.employeeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feuji.employeeservice.entity.UserLoginEntity;
import com.feuji.employeeservice.service.UserLoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserLoginController {
	
	@Autowired
    private UserLoginService userLoginService;
	
//	@Autowired
//	private EmailService emailService;
	
	@PostMapping("/login")
	public ResponseEntity<UserLoginEntity> loginUser(@RequestBody UserLoginEntity userCredentials) {
		log.info("user details :{}",userCredentials);
	    UserLoginEntity loggedInUser;
			loggedInUser = userLoginService.loginUser(userCredentials.getUserEmail(), userCredentials.getUserPassword());
	    if (loggedInUser != null) {
	    	log.info("Login Success :{}",userCredentials);
	        return ResponseEntity.ok(loggedInUser); // Return the UserLoginEntity if login is successful
	    } else {
	    	log.info("Login Fails :{}",userCredentials);
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Return 401 Unauthorized if login fails
	    }
	}
	
	@GetMapping("/checkUniqueEmail")
    public ResponseEntity<Boolean> checkUniqueEmail(@RequestParam("email") String email) {
        boolean isUnique = userLoginService.isEmailUnique(email);
        return ResponseEntity.ok(isUnique);
    }

//    @PostMapping("/forgot-password")
//    public boolean forgotPassword(@RequestParam String userEmail) {
//        return userLoginService.forgotPassword(userEmail);
//    }

}