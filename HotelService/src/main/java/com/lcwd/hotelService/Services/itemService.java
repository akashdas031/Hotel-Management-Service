package com.lcwd.hotelService.Services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lcwd.hotelService.Entities.Items;

@FeignClient(name = "ITEM-SERVICE")
public interface itemService {

	@GetMapping("/menus/{menuId}")
	public List<Items> getItemsByMenuId(@PathVariable("menuId") String menuId);
}
