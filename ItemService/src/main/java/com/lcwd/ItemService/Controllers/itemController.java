package com.lcwd.ItemService.Controllers;

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

import com.lcwd.ItemService.Entities.Items;
import com.lcwd.ItemService.Services.itemService;

@RestController
@RequestMapping("/items")
public class itemController {

	@Autowired
	private itemService itemServ;
	
	//create item
	@PostMapping
	public ResponseEntity<Items> createItem(@RequestBody Items item){
		Items items = itemServ.createItem(item);
		return ResponseEntity.status(HttpStatus.CREATED).body(items);
	}
	
	//get all items
	@GetMapping
	public ResponseEntity<List<Items>> getAllItems(){
		List<Items> allItems = itemServ.getAllItems();
		return ResponseEntity.ok(allItems);
	}
	//get item by item id
	@GetMapping("/{itemId}")
	public ResponseEntity<Items> getItemByItemId(@PathVariable("itemId") String itemId){
		Items itemByItemId = itemServ.getItemByItemId(itemId);
		return ResponseEntity.ok(itemByItemId);
	}
	
	//get item by menu id
	@GetMapping("/menus/{menuId}")
	public ResponseEntity<List<Items>> getItemByMenuId(@PathVariable("menuId") String menuId){
		List<Items> itemByMenuId = itemServ.getItemByMenuId(menuId);
		return ResponseEntity.ok(itemByMenuId);
	}
}
