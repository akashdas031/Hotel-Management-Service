package com.lcwd.RatingService.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.RatingService.Entities.Ratings;
import com.lcwd.RatingService.Services.ratingService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/ratings")
public class ratingControllers {

	@Autowired
	private ratingService ratingServ;
	//create ratings
	@PostMapping
	public ResponseEntity<Ratings> createRating(@RequestBody Ratings rating){
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingServ.createRating(rating));
	}
	
	//get all ratings 
	@GetMapping
	@CircuitBreaker(name="allratingCircuit",fallbackMethod = "allratingFallback")
	public ResponseEntity<List<Ratings>> getAllRatings(){
		return ResponseEntity.ok(ratingServ.getAllRatings());
	}
	
	//fallback method for get list of ratings api
	public ResponseEntity<List<Ratings>> allratingFallback(Exception ex){
		Ratings build = Ratings.builder().userId("xxxxxx").hotelId("xxxxxx").rating(10).feedback("Dummy rating because of some service is down").build();
	    List<Ratings> ratings=new ArrayList<Ratings>();
	    ratings.add(build);
		return new ResponseEntity<List<Ratings>>(ratings,HttpStatus.OK);
	}
	
	
	//get Ratings by rating id
	
	@GetMapping("/users/{userId}")
	@CircuitBreaker(name="ratingByHotelIdCircuit",fallbackMethod = "ratingByHotelIdFallback")
	public ResponseEntity<List<Ratings>> getRatingsByUserID(@PathVariable String userId){
		return ResponseEntity.ok(ratingServ.getRatingByUserId(userId));
	}
	//fallback method for get rating by user id  api
	
	public ResponseEntity<List<Ratings>> ratingByHotelIdFallback(String userId,Exception ex){
		Ratings build = Ratings.builder().userId("xxxxxx").hotelId("xxxxxx").rating(10).feedback("Dummy rating because of some service is down").build();
	    List<Ratings> ratings=new ArrayList<Ratings>();
	    ratings.add(build);
		return new ResponseEntity<List<Ratings>>(ratings,HttpStatus.OK);
	}
	
	
	// get ratings by hotel id
	
	@GetMapping("/hotels/{hotelId}")
	@CircuitBreaker(name="hotelRatingCircuit",fallbackMethod = "hotelRatingFallback")
	public ResponseEntity<List<Ratings>> getRatingsByHotelId(@PathVariable String hotelId){
		return ResponseEntity.ok(ratingServ.getRatingByHotelId(hotelId));
	}
	//fallback method for hotel rating service 
	
	public ResponseEntity<List<Ratings>> hotelRatingFallback(String hotelId,Exception ex){
			Ratings build = Ratings.builder().userId("xxxxxx").hotelId("xxxxxx").rating(10).feedback("Dummy rating because of some service is down").build();
		    List<Ratings> ratings=new ArrayList<Ratings>();
		    ratings.add(build);
			return new ResponseEntity<List<Ratings>>(ratings,HttpStatus.OK);
		}
}
