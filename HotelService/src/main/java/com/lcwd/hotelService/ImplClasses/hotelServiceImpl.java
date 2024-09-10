package com.lcwd.hotelService.ImplClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.hotelService.Entities.Hotel;
import com.lcwd.hotelService.Entities.Menu;
import com.lcwd.hotelService.Entities.Staffs;
import com.lcwd.hotelService.Exceptions.ResourceNotFoundException;
import com.lcwd.hotelService.Repositories.hotelRepository;
import com.lcwd.hotelService.Services.hotelService;
@Service
public class hotelServiceImpl implements hotelService{

	@Autowired
	private hotelRepository hservice;
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Hotel addHotel(Hotel hotel) {
		String randomUUID = UUID.randomUUID().toString();
		hotel.setId(randomUUID);
	
			
		return hservice.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		List<Hotel> hotels = hservice.findAll();
		for(Hotel h:hotels){
			String id = h.getId();
			ArrayList<Staffs> staffs = restTemplate.getForObject("http://STAFF-SERVICE/staffs/hotel/"+id, ArrayList.class);
		    
			
			h.setStaff(staffs);
			ArrayList menu = restTemplate.getForObject("http://MENU-SERVER/menus/hotels/"+id, ArrayList.class);
			h.setMenu(menu);
		}
		return hotels;
	}

	@Override
	public Hotel getHotelById(String Id) {
		
		 Hotel hotel = hservice.findById(Id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id not available in our server...!!!"));
		 String hid = hotel.getId();
			ArrayList<Menu> menu = restTemplate.getForObject("http://MENU-SERVER/menus/hotels/"+hid, ArrayList.class);
			ArrayList<Staffs> staffs = restTemplate.getForObject("http://STAFF-SERVICE/staffs/hotel/"+hid, ArrayList.class);
            hotel.setStaff(staffs);
			hotel.setMenu(menu);
          return hotel;
		 
	}

	
	
}
