package rs.ac.bg.etf.dipl.mapper.api;

import rs.ac.bg.etf.dipl.dto.movie.CreateMovieDto;
import rs.ac.bg.etf.dipl.dto.movie.MovieDto;
import rs.ac.bg.etf.dipl.dto.movie.UpdateMovieDto;
import rs.ac.bg.etf.dipl.web.model.movie.MovieRequest;
import rs.ac.bg.etf.dipl.web.model.movie.MovieResponse;
import rs.ac.bg.etf.dipl.web.model.movie.UpdateMovieRequest;

public interface MovieApiMapper {

	CreateMovieDto apiToDto(MovieRequest movie) ;

	MovieResponse dtoToApi(MovieDto movie);
	
	UpdateMovieDto updateMovieRequestToDto(UpdateMovieRequest movie);
}
