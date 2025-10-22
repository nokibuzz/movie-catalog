package rs.ac.bg.etf.dipl.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import rs.ac.bg.etf.dipl.dto.actor.ActorDto;
import rs.ac.bg.etf.dipl.dto.movie.CreateMovieDto;
import rs.ac.bg.etf.dipl.dto.movie.MovieDto;
import rs.ac.bg.etf.dipl.dto.movie.UpdateMovieDto;
import rs.ac.bg.etf.dipl.filter.MovieSearchFilter;

public interface MovieDAO {

	Page<MovieDto> getMovies(MovieSearchFilter filter);
	
	MovieDto getMovieById(Long id);
	
	MovieDto addMovie(CreateMovieDto movie);
	
	void updateMovie(Long id, UpdateMovieDto updateMovieDto, List<ActorDto> actors);
	
	void deleteMovie(Long id);
}
