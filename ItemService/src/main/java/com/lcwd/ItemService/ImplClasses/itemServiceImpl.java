package com.lcwd.ItemService.ImplClasses;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.ItemService.Entities.Items;
import com.lcwd.ItemService.Exceptions.ItemNotFoundException;
import com.lcwd.ItemService.Repositories.itemRepository;
import com.lcwd.ItemService.Services.itemService;
@Service
public class itemServiceImpl implements itemService{

	@Autowired
	private itemRepository itemRepo;
	
	@Override
	public Items createItem(Items items) {
		// create item
		String id = UUID.randomUUID().toString();
		items.setItemId(id);
		return itemRepo.save(items);
	}

	@Override
	public List<Items> getAllItems() {
		// get all items
		return itemRepo.findAll();
	}

	@Override
	public Items getItemByItemId(String itemId) {
		// get item by item id
		return itemRepo.findById(itemId).orElseThrow(()->new ItemNotFoundException("Item with given id is not available on server...!!!"));
	}

	@Override
	public List<Items> getItemByMenuId(String menuId) {
		// TODO Auto-generated method stub
		return itemRepo.findByMenuId(menuId);
	}

}
