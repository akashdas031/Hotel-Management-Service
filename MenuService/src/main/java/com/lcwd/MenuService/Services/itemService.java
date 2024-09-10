package com.lcwd.MenuService.Services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lcwd.MenuService.Entities.Items;

@FeignClient(name = "ITEM-SERVICE")
public interface itemService {
	
	@GetMapping("/items/menus/{menuId}")
	public List<Items> getItemsByMenuId(@PathVariable("menuId") String menuId);

}
