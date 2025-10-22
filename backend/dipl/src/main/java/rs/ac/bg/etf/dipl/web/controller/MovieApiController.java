package rs.ac.bg.etf.dipl.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.bg.etf.dipl.enumeration.Genre;
import rs.ac.bg.etf.dipl.facade.MovieFacade;
import rs.ac.bg.etf.dipl.mapper.FilterMapper;
import rs.ac.bg.etf.dipl.web.api.MovieApi;
import rs.ac.bg.etf.dipl.web.model.movie.MovieResponse;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class MovieApiController implements MovieApi {

	@Autowired
	private MovieFacade movieFacade;

	@Override
	public ResponseEntity<Page<MovieResponse>> getMoviesByParams(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "genre", required = false) Genre genre, @ApiIgnore Pageable pageable) {

		return ResponseEntity.ok(movieFacade.getMoviesByParams(
				FilterMapper.toSearchFilter(title, genre, pageable)));
	}

	@Override
	public ResponseEntity<MovieResponse> getMovieById(Long id) {
		return ResponseEntity.ok(movieFacade.getMovieById(id));
	}
}
