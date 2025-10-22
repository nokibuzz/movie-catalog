package rs.ac.bg.etf.dipl.repository;

import org.springframework.data.domain.Page;

import rs.ac.bg.etf.dipl.domain.Movie;
import rs.ac.bg.etf.dipl.filter.MovieSearchFilter;

public interface MovieRepoCustom {

	Page<Movie> findAllByFilter(MovieSearchFilter filter);
}
