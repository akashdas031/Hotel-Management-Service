package com.lcwd.RatingService.Services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lcwd.RatingService.Entities.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface hotelService {

	@GetMapping("/hotels/{hotelId}")
	public Hotel getHotel(@PathVariable("hotelId") String hotelId);
	
		
	
}
