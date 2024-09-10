package com.lcwd.MenuService.Entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
@Entity
@Table(name="Menu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu {

	@Id
	private String Id;
	private String type;
	private String description;
	private String hotelId;
	@Transient
	private List<Items> items;
}
