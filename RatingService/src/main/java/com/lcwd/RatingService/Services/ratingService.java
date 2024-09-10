package com.lcwd.RatingService.Services;

import java.util.List;

import com.lcwd.RatingService.Entities.Ratings;

public interface ratingService {

	//create 
	Ratings createRating(Ratings ratings);
	
	//get all ratings
	
	List<Ratings> getAllRatings();
	
	//get all by user id
	
	List<Ratings> getRatingByUserId(String userId);
	
	//get all by hotel id
	
	List<Ratings> getRatingByHotelId(String hotelId);
}
