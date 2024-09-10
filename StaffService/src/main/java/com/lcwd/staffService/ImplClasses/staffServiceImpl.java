package com.lcwd.staffService.ImplClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.staffService.Entities.Staffs;
import com.lcwd.staffService.Exceptions.StaffNotFoundException;
import com.lcwd.staffService.Repositories.staffRepository;
import com.lcwd.staffService.Services.staffService;
@Service
public class staffServiceImpl implements staffService{

	@Autowired
	private staffRepository staffRepo;
	//create staff
	@Override
	public Staffs createStaff(Staffs staff) {
		// TODO Auto-generated method stub
		String Id = UUID.randomUUID().toString();
		staff.setStaffId(Id);
		return staffRepo.save(staff);
	}
    //get all staffs
	@Override
	public List<Staffs> getAllStaffs() {
		// TODO Auto-generated method stub
		return staffRepo.findAll();
	}
    //get staff by staffId
	@Override
	public Staffs getStaffByStaffId(String staffId) {
		// TODO Auto-generated method stub
		return staffRepo.findById(staffId).orElseThrow(()-> new StaffNotFoundException("Staff with given id is not available on the server...!!!"));
	}
	//get staff by hotel Id
	@Override
	public List<Staffs> getStaffByHotelId(String hotelId) {
		List<Staffs> findByHotelId = staffRepo.findByHotelId(hotelId);
		return findByHotelId;
	}

	
	
	

}
