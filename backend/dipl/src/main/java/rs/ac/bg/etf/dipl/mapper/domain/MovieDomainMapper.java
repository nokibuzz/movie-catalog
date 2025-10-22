package rs.ac.bg.etf.dipl.mapper.domain;

import rs.ac.bg.etf.dipl.domain.Movie;
import rs.ac.bg.etf.dipl.dto.movie.CreateMovieDto;
import rs.ac.bg.etf.dipl.dto.movie.MovieDto;

public interface MovieDomainMapper {

	Movie dtoToDomain(CreateMovieDto movie);

	MovieDto domainToDto(Movie movie);
}
