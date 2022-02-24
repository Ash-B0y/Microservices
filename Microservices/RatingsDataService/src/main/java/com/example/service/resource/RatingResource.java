package com.example.service.resource;

import java.util.Arrays;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.model.Rating;
import com.example.service.model.UserRating;

@RestController
@RequestMapping("/ratingsData")
public class RatingResource {
	
	@GetMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId,4);
	}
	
	@GetMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserRating(Arrays.asList(
				new Rating("1",4),
				new Rating("2",3)));
		
		return userRating;
	}

}
