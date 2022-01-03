package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieMinDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService{
	
	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
		return new MovieDTO(entity, entity.getGenre());
	}
	
	@Transactional(readOnly = true)
	public Page<MovieMinDTO> findByGenrePaged(Long genreId, Pageable pageable) {
		genreId = (genreId == 0) ? null : genreId;
		Page<MovieMinDTO> page = repository.findByGenrePaged(genreId, pageable);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<ReviewDTO> findReviews(Long movieId) {
		List<Review> list = reviewRepository.findReview(movieId);
		return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
	}
	
}
