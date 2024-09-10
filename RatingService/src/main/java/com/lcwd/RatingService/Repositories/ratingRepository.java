package com.lcwd.RatingService.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lcwd.RatingService.Entities.Ratings;

public interface ratingRepository extends MongoRepository<Ratings, String>{

	//custom finder method
	
	List<Ratings> findByUserId(String userId);
	List<Ratings> findByHotelId(String hotelId);
}
