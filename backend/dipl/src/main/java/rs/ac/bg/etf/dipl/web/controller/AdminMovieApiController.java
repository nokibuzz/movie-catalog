package rs.ac.bg.etf.dipl.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.bg.etf.dipl.enumeration.Genre;
import rs.ac.bg.etf.dipl.facade.MovieFacade;
import rs.ac.bg.etf.dipl.mapper.FilterMapper;
import rs.ac.bg.etf.dipl.web.api.AdminMovieApi;
import rs.ac.bg.etf.dipl.web.model.movie.MovieRequest;
import rs.ac.bg.etf.dipl.web.model.movie.MovieResponse;
import rs.ac.bg.etf.dipl.web.model.movie.UpdateMovieRequest;

@RestController
public class AdminMovieApiController implements AdminMovieApi {

	@Autowired
	private MovieFacade movieFacade;

	@Override
	public ResponseEntity<Page<MovieResponse>> getMoviesByParams(String title, Genre genre, Pageable pageable) {
		return ResponseEntity.ok(movieFacade.getMoviesByParams(
				FilterMapper.toSearchFilter(title, genre, pageable)));
	}

	@Override
	public ResponseEntity<MovieResponse> getMovieById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(movieFacade.getMovieById(id));
	}

	@Override
	public ResponseEntity<MovieResponse> save(@Valid MovieRequest movie) {
		return new ResponseEntity<>(movieFacade.addMovie(movie), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid UpdateMovieRequest movie) {
		movieFacade.updateMovie(id, movie);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		movieFacade.deleteMovie(id);
		return ResponseEntity.noContent().build();
	}
}
