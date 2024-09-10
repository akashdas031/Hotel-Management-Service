package com.lcwd.RatingService.ImplClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.RatingService.Entities.Hotel;
import com.lcwd.RatingService.Entities.Ratings;
import com.lcwd.RatingService.Repositories.ratingRepository;
import com.lcwd.RatingService.Services.hotelService;
import com.lcwd.RatingService.Services.ratingService;

@Service
public class ratingServiceImpl implements ratingService{

	@Autowired
	private ratingRepository ratingRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private hotelService hotelServ;
	
	@Override
	public Ratings createRating(Ratings ratings) {
		
		return ratingRepo.save(ratings);
	}

	@Override
	public List<Ratings> getAllRatings() {
		
		 List<Ratings> ratings = ratingRepo.findAll();
		 for(Ratings rating:ratings) {
			 String hotelId2 = rating.getHotelId();
			// Hotel hotelList = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+hotelId2, Hotel.class);
			 Hotel hotelList = hotelServ.getHotel(hotelId2);
			 rating.setHotel(hotelList);
		 }
		 return ratings;
	}

	@Override
	public List<Ratings> getRatingByUserId(String userId) {
		
		List<Ratings> ratings = ratingRepo.findByUserId(userId);
		 for(Ratings rating:ratings) {
			 String hotelId2 = rating.getHotelId();
			 Hotel hotelList = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+hotelId2, Hotel.class);
			 rating.setHotel(hotelList);
		 }
		return ratings;
	}

	@Override
	public List<Ratings> getRatingByHotelId(String hotelId) {
		
		
		 List<Ratings> hotels = ratingRepo.findByHotelId(hotelId);
		for(Ratings h : hotels) {
			
			String hotelId2 = h.getHotelId();
			Hotel hotelList = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+hotelId2, Hotel.class);
			h.setHotel(hotelList);
		}
		return hotels;
	}

}
