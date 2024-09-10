package com.lcwd.ItemService.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.ItemService.Entities.Items;

public interface itemRepository extends JpaRepository<Items, String>{
	//get item by menu id
	
	public List<Items> findByMenuId(String menuId);

}
