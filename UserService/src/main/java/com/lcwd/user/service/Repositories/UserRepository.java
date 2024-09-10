package com.lcwd.user.service.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

	//if we want to implement any custom method or query we have to write here
}
