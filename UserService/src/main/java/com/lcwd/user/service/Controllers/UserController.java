package com.lcwd.user.service.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.Services.UserService;
import com.lcwd.user.service.entities.User;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	//create
	@PostMapping
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User saveUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
	}
	//single user get
	@GetMapping("/{userId}")
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Normal') || hasAuthority('Admin')") 
	@CircuitBreaker(name = "ratingHotelCircuit",fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User> getUser(@PathVariable String userId){
		 User user = userService.getUser(userId);
		 return ResponseEntity.ok(user);
	}
	//create fall back method for circuit breaker
	
	public ResponseEntity<User> ratingHotelFallBack(String userId,Exception ex){
		User user = User.builder().email("Dummy@gmail.com").name("Dummy").about("This is a dummy user because some service is down").userId("12345").build();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	//all user get
	@GetMapping
	@PreAuthorize("hasAuthority('Admin') || hasAuthority('SCOPE_internal')")
	@CircuitBreaker(name = "alluserCircuit",fallbackMethod = "alluserFallback")
	//@RateLimiter(name="allUserRate",fallbackMethod = "alluserFallback")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}
	
	//fall back method for all user get api
	
	public ResponseEntity<List<User>> alluserFallback(Exception ex){
		User user = User.builder().email("Dummy@gmail.com").name("Dummy").about("This is a dummy user because some service is down").userId("12345").build();
		List<User> l=new ArrayList<>();
		l.add(user);
		return new ResponseEntity<List<User>>(l,HttpStatus.OK);
	}
}
