package com.lcwd.hotelService.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.hotelService.Entities.Hotel;

public interface hotelRepository extends JpaRepository<Hotel, String>{

}
