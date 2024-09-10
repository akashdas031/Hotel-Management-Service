package com.lcwd.staffService.Services;

import java.util.List;
import java.util.Stack;

import com.lcwd.staffService.Entities.Staffs;

public interface staffService {

	//create
	
	public Staffs createStaff(Staffs staff);
	
	//get All Staff
	
	public List<Staffs> getAllStaffs();
	
	//get staff by staff Id
	
	public Staffs getStaffByStaffId(String staffId);
	
	
	//get staff by hotel id
	
	public List<Staffs> getStaffByHotelId(String hotelId);
	
	
}
