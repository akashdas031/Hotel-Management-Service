package com.lcwd.MenuService.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Items {

	private String itemId;
	private String itemName;
	private String menuId;
	private String description;
}
