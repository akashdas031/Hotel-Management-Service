package com.lcwd.hotelService.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Hotel_Table")
public class Hotel {

	@Id
	@Column(name="ID")
	private String id;
	@Column(name="NAME")
	private String name;
	@Column(name="ABOUT")
	private String about;
	
	@Transient
	private List<Staffs> staff =new ArrayList<Staffs>();
	@Transient
	private List<Menu> menu=new ArrayList<Menu>();
	
	
}
