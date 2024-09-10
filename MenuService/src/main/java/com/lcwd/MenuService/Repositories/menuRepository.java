package com.lcwd.MenuService.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.MenuService.Entities.Menu;

public interface menuRepository extends JpaRepository<Menu, String>{

	public List<Menu> findByHotelId(String hotelId);
}
