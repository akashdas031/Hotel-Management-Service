package com.lcwd.hotelService.Controllers;

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

import com.lcwd.hotelService.Entities.Hotel;
import com.lcwd.hotelService.Entities.Menu;
import com.lcwd.hotelService.Entities.Staffs;
import com.lcwd.hotelService.Entities.Staffs.StaffsBuilder;
import com.lcwd.hotelService.Services.hotelService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/hotels")
public class hotelController {

	@Autowired
	private hotelService hs;
	//create hotel
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		Hotel createdHotel = hs.addHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
	}
	
	//get all hotels
	@GetMapping
	@CircuitBreaker(name="hotelsCircuit",fallbackMethod = "hotelCircuit")
	public ResponseEntity<List<Hotel>> getAllHotels(){
		List<Hotel> allHotel = hs.getAllHotel();
		return ResponseEntity.ok(allHotel);
	}
	//fall back method for get all hotels circuit
	public ResponseEntity<List<Hotel>> hotelCircuit(Exception ex){
		Menu menu = Menu.builder().Id("XXXXXXXX").type("Dummy").description("No data found because Menu service is down...").build();
		Staffs staff = Staffs.builder().staffId("XXXXXX").hotelId("XXXXXXX").name("Dummy").designation("Dummy").about("No data found because Staff service is down...").build();
		List<Staffs> l=new ArrayList<>();
		l.add(staff);
		List<Menu> m=new ArrayList<>();
		m.add(menu);
		Hotel hotel = Hotel.builder().id("XXXXXXXX").name("Dummy").about("No data found because some service is down...").staff(l).menu(m).build();
		List<Hotel> h=new ArrayList<Hotel>();
		h.add(hotel);
		return new ResponseEntity<List<Hotel>>(h,HttpStatus.OK);
		
	}
	
	//get single hotel using hotel id
	@GetMapping("/{Id}")
	@CircuitBreaker(name="hotelMenuStaffCircuit",fallbackMethod = "menuStaffFallBack")
	public ResponseEntity<Hotel> getSingleHotel(@PathVariable String Id){
		Hotel hotelById = hs.getHotelById(Id);
		return ResponseEntity.ok(hotelById);
	}
	
	//fallback method for menu staff hotel service 
	
	public ResponseEntity<Hotel> menuStaffFallBack(String Id,Exception ex){
		Menu menu = Menu.builder().Id("XXXXXXXX").type("Dummy").description("No data found because Menu service is down...").build();
		Staffs staff = Staffs.builder().staffId("XXXXXX").hotelId(Id).name("Dummy").designation("Dummy").about("No data found because Staff service is down...").build();
		List<Staffs> l=new ArrayList<>();
		l.add(staff);
		List<Menu> m=new ArrayList<>();
		m.add(menu);
		Hotel hotel = Hotel.builder().id(Id).name("Dummy").about("No data found because some service is down...").staff(l).menu(m).build();
		return new ResponseEntity<Hotel>(hotel,HttpStatus.OK);
	}
	
}
