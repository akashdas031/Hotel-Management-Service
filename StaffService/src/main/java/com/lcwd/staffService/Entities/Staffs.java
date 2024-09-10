package com.lcwd.staffService.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Staffs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Staffs {

	@Id
	@Column(name="STAFF-ID")
	private String staffId;
	@Column(name="HOTEL-ID")
	private String hotelId;
	@Column(name="NAME")
	private String name;
	@Column(name="ABOUT")
	private String about;
	@Column(name="DESIGNATION")
	private String designation;
	
}
