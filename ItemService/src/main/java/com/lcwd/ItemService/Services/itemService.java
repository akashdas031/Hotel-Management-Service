package com.lcwd.ItemService.Services;

import java.util.List;

import com.lcwd.ItemService.Entities.Items;

public interface itemService {

	//create Item
	public Items createItem(Items items);
	//get all item
	public List<Items> getAllItems();
	//get item by item id
	public Items getItemByItemId(String itemId);
	//get item by menu id
	public List<Items> getItemByMenuId(String menuId);
}
