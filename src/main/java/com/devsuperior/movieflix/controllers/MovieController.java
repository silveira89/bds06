package com.devsuperior.movieflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieMinDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {
	
	@Autowired
	private MovieService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
		MovieDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<MovieMinDTO>> findByGenrePaged(
			@RequestParam(value = "genreId", defaultValue = "0") Long genreId,
			Pageable pageable) {
		Page<MovieMinDTO> page = service.findByGenrePaged(genreId, pageable);
		return ResponseEntity.ok().body(page);
	}
	
	@GetMapping(value = "/{id}/{review}")
	public ResponseEntity<List<ReviewDTO>> findReview(@PathVariable Long id) {
		List<ReviewDTO> dto = service.findReviews(id);
		return ResponseEntity.ok().body(dto);
	}
	
}
