package com.lcwd.staffService.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.staffService.Entities.Staffs;

public interface staffRepository extends JpaRepository<Staffs, String>{

	public List<Staffs> findByHotelId(String hotelId);
}
