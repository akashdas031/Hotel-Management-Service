package com.lcwd.hotelService.Entities;

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
public class Menu {

	private String Id;
	private String type;
	private String description;
	private String hotelId;
}
