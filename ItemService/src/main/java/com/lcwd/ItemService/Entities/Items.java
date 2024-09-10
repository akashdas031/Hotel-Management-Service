package com.lcwd.ItemService.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ITEMS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Items {

	@Id
	private String itemId;
	private String itemName;
	private String menuId;
	private String description;
}
