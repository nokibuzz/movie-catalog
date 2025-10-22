package rs.ac.bg.etf.dipl.facade;

import org.springframework.data.domain.Page;

import rs.ac.bg.etf.dipl.filter.MovieSearchFilter;
import rs.ac.bg.etf.dipl.web.model.movie.MovieRequest;
import rs.ac.bg.etf.dipl.web.model.movie.MovieResponse;
import rs.ac.bg.etf.dipl.web.model.movie.UpdateMovieRequest;

public interface MovieFacade {

	Page<MovieResponse> getMoviesByParams(MovieSearchFilter filter);
	
	MovieResponse getMovieById(Long id);
	
	MovieResponse addMovie(MovieRequest movie);
	
	void updateMovie(Long id, UpdateMovieRequest movie);
	
	void deleteMovie(Long id);
}
