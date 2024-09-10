package com.lcwd.MenuService.ImplClasses;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.MenuService.Entities.Items;
import com.lcwd.MenuService.Entities.Menu;
import com.lcwd.MenuService.Exceptions.MenuNotFoundException;
import com.lcwd.MenuService.Repositories.menuRepository;
import com.lcwd.MenuService.Services.itemService;
import com.lcwd.MenuService.Services.menuService;

@Service
public class menuServiceImpl implements menuService {

	@Autowired
	private menuRepository menuServ;
	@Autowired
	private itemService itemServ;
	@Autowired
	private RestTemplate restTemplate;
	

	private Logger logger=LoggerFactory.getLogger(menuServiceImpl.class);
	
	int retryCount;

	@Override
	public Menu createMenu(Menu menu) {
		String Id = UUID.randomUUID().toString();
		menu.setId(Id);
		return menuServ.save(menu);
	}

	@Override
	public List<Menu> getAllMenu() {
		List<Menu> menus = menuServ.findAll();
		for(Menu m:menus) {
			String id = m.getId();
			List<Items> itemsByMenuId = itemServ.getItemsByMenuId(id);
			m.setItems(itemsByMenuId);
		}
		return menus;
	}

	@Override
	public Menu getMenuById(String Id) {
		
		 Menu menu = menuServ.findById(Id).orElseThrow(()-> new MenuNotFoundException("Menu with given id not available...!!!"));
		 List<Items> items = itemServ.getItemsByMenuId(Id);
		 menu.setItems(items);
		 return menu;
	}

	@Override
	public List<Menu> getMenuByHotelId(String Id) {
		
		List<Menu> menuByHotels = menuServ.findByHotelId(Id);
		logger.info(""+retryCount,menuByHotels);
		retryCount++;
		for(Menu m:menuByHotels) {
			String mid = m.getId();
			List<Items> items = itemServ.getItemsByMenuId(mid);
			m.setItems(items);
		}
		return menuByHotels;
	}
	
	
}
