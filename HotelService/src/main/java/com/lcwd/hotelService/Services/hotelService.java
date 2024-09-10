package com.lcwd.hotelService.Services;

import java.util.List;

import com.lcwd.hotelService.Entities.Hotel;

public interface hotelService {

	//create
	Hotel addHotel(Hotel hotel);
	
	//get all hotel
	
	List<Hotel> getAllHotel();
	
	//get single hotel using hotel id
	
	Hotel getHotelById(String Id);
	
	
}
