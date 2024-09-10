package com.lcwd.MenuService.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.MenuService.Entities.Menu;
import com.lcwd.MenuService.ImplClasses.menuServiceImpl;
import com.lcwd.MenuService.Services.menuService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;


@RestController
@RequestMapping("/menus")
public class MenuController {

	@Autowired
	private menuService menuServ;
	
	private Logger logger=LoggerFactory.getLogger(menuServiceImpl.class);
	//create Menu
	
	@PostMapping
	public ResponseEntity<Menu> createMenu(@RequestBody Menu menu){
		Menu createMenu = menuServ.createMenu(menu);
		return ResponseEntity.status(HttpStatus.CREATED).body(createMenu);
	}
	
	//get all Menu
	@GetMapping
	@CircuitBreaker(name="menuItem",fallbackMethod = "menuItem")
	public ResponseEntity<List<Menu>> getAllMenu(){
		List<Menu> allMenu = menuServ.getAllMenu();
		return ResponseEntity.ok(allMenu);
	}
	
	//fallback method for get all menu api
	
	public ResponseEntity<List<Menu>> menuItem(Exception ex){
		Menu menu = Menu.builder().type("Dummy").description("Dummy data because some service is down...").hotelId("xxxxxxxx").build();
		List<Menu> l =new ArrayList<Menu>();
		l.add(menu);
		return new ResponseEntity<List<Menu>>(l,HttpStatus.OK);

	}
	
	//get all menu by menu id
	@GetMapping("/{Id}")
	@CircuitBreaker(name="menuItemCircuitBreaker",fallbackMethod = "menuItemFallback")
	public ResponseEntity<Menu> getMenuById(@PathVariable("Id") String Id){
		 Menu menuById = menuServ.getMenuById(Id);
		 return ResponseEntity.ok(menuById);
	}
	
	//fall back method for menu hotel circuit breaker
	
		public ResponseEntity<List<Menu>> menuItemFallback(String hotelId,Exception ex){
			Menu menu = Menu.builder().type("Dummy").description("Dummy data because some service is down...").hotelId("xxxxxxxx").build();
			List<Menu> l =new ArrayList<Menu>();
			l.add(menu);
			return new ResponseEntity<List<Menu>>(l,HttpStatus.OK);
		}
	int retryCount=1;
	//get menu by hotelId
	@GetMapping("/hotels/{hotelId}")
	//@CircuitBreaker(name="hotelMenu",fallbackMethod = "hotelMenu")
	@Retry(name="hotelRetry",fallbackMethod = "hotelMenu ")
	public ResponseEntity<List<Menu>> getMenuByHotelId(@PathVariable("hotelId") String hotelId){
		
		List<Menu> menuByHotelId = menuServ.getMenuByHotelId(hotelId);
        logger.info(""+retryCount,menuByHotelId);
        retryCount++; 
		return ResponseEntity.ok(menuByHotelId);
	}
	//fall back method for get menu by hotel id
	
	public ResponseEntity<List<Menu>> hotelMenu(String hotelId,Exception ex){
		Menu menu = Menu.builder().type("Dummy").description("Dummy data because some service is down...").hotelId("xxxxxxxx").build();
		List<Menu> l =new ArrayList<Menu>();
		l.add(menu);
		return new ResponseEntity<List<Menu>>(l,HttpStatus.OK);
	}
	
	
	
}
