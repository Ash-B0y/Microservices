package com.example.service.resource;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.example.service.model.CatalogItem;
import com.example.service.model.Movie;
import com.example.service.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId")String userId) {
		UserRating userRatings = restTemplate.getForObject("http://ratings-data-service/ratingsData/users/"+userId, UserRating.class);		
		return userRatings.getUserRating().stream().map(rating-> {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(),"Desc", rating.getRating());
		}).collect(Collectors.toList());
				
	}
 
}

/*
 * Movie movie = webClientBuilder.build().get()
 * .uri("http://localhost:8090/movies/"+rating.getMovieId())
 * .retrieve().bodyToMono(Movie.class) .block();
 */
		
