package com.example.imdb.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.domain.Movie;
import com.example.imdb.repository.MovieService;

@RestController
@RequestScope
@RequestMapping("/movies")
@Validated
@CrossOrigin
public class ImdbRestController {
	private final MovieService movieService;

	// Constructor Injection
	public ImdbRestController(MovieService movieService) {
		this.movieService = movieService;
	}

	// http://localhost:8100/imdb/api/v1/movies/1
	@GetMapping(value="{id}")
	public Movie findMovieByImdb(@PathVariable int id) {
		return movieService.findMovieById(id);
	}

	// http://localhost:8100/imdb/api/v1/movies?pageNo=0&pageSize=25
	@GetMapping(params = { "pageNo", "pageSize" })
	public List<Movie> findMoviesByPage(@RequestParam int pageNo, @RequestParam int pageSize) {
		return movieService.findAllMovies().stream()
				                           .skip(pageNo * pageSize)
				                           .limit(pageSize)
				                           .toList();
	}

}
