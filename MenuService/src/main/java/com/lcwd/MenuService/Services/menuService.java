package com.lcwd.MenuService.Services;

import java.util.List;

import com.lcwd.MenuService.Entities.Menu;


public interface menuService {
	
	//create menu
	public Menu createMenu(Menu menu);
	//get all menu
	public List<Menu> getAllMenu();
	//get menu by menu id
	public Menu getMenuById(String Id);
	
	//get menu by hotel Id
	
	public List<Menu> getMenuByHotelId(String Id);
	
	

}
