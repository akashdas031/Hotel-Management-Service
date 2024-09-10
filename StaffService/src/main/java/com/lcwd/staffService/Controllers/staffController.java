package com.lcwd.staffService.Controllers;

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

import com.lcwd.staffService.Entities.Staffs;
import com.lcwd.staffService.Services.staffService;


@RestController
@RequestMapping("/staffs")
public class staffController {
	
	@Autowired
	private staffService stafServ;
	
	//create Staffs
	@PostMapping
	public ResponseEntity<Staffs> createStaff(@RequestBody Staffs staff){
		Staffs staff2 = stafServ.createStaff(staff);
		return ResponseEntity.status(HttpStatus.CREATED).body(staff2);
	}
	
	//get all staffs
	@GetMapping
	public ResponseEntity<List<Staffs>> getAllStaffs(){
		
		List<Staffs> allStaffs = stafServ.getAllStaffs();
		return ResponseEntity.ok(allStaffs);
	}
	
	//get staff by staff Id
	@GetMapping("/{staffId}")
	public ResponseEntity<Staffs> getStaffByStaffId(@PathVariable("staffId") String staffId){
		Staffs staffByStaffId = stafServ.getStaffByStaffId(staffId);
		return ResponseEntity.ok(staffByStaffId);
	}
	
	//get staff by hotel id
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<Staffs>> getStaffByHotelId(@PathVariable("hotelId") String hotelId){
		List<Staffs> staffByHotelId = stafServ.getStaffByHotelId(hotelId);
		return ResponseEntity.ok(staffByHotelId);
	}

}
