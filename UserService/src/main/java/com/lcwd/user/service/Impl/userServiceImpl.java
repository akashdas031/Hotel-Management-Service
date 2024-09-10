package com.lcwd.user.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.Exceptions.ResourceNotFoundException;
import com.lcwd.user.service.Repositories.UserRepository;
import com.lcwd.user.service.Services.UserService;
import com.lcwd.user.service.entities.User;
@Service
public class userServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Override
	public User saveUser(User user) {
		//generate unique user id
		String randomUserId =UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		 List<User> user = userRepository.findAll();
		 for(User u:user) {
			 String userId = u.getUserId();
			 ArrayList ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+userId, ArrayList.class);
			 u.setRatings(ratings);
		 }
		 return user;
	}

	@Override
	public User getUser(String userId) {
		
		User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with given id not found on server...!! :"+userId));
		//calling rating service with api get ratings with user id
		//localhost:8288/ratings/users/90a4494a-e959-46a1-be39-1813b3a315d4
		ArrayList ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), ArrayList.class);
	
		user.setRatings(ratings);
		return user;
	}

}
