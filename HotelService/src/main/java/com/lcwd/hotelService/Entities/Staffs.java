package com.lcwd.hotelService.Entities;

import jakarta.persistence.Column;
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
public class Staffs {

	
	private String staffId;
	
	private String hotelId;
	
	private String name;
	
	private String about;
	
	private String designation;
}
