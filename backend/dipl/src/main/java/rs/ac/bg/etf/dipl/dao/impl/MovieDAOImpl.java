package rs.ac.bg.etf.dipl.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rs.ac.bg.etf.dipl.dao.MovieDAO;
import rs.ac.bg.etf.dipl.domain.Actor;
import rs.ac.bg.etf.dipl.domain.Movie;
import rs.ac.bg.etf.dipl.dto.actor.ActorDto;
import rs.ac.bg.etf.dipl.dto.movie.CreateMovieDto;
import rs.ac.bg.etf.dipl.dto.movie.MovieDto;
import rs.ac.bg.etf.dipl.dto.movie.UpdateMovieDto;
import rs.ac.bg.etf.dipl.filter.MovieSearchFilter;
import rs.ac.bg.etf.dipl.mapper.domain.ActorDomainMapper;
import rs.ac.bg.etf.dipl.mapper.domain.ImageDomainMapper;
import rs.ac.bg.etf.dipl.mapper.domain.MovieDomainMapper;
import rs.ac.bg.etf.dipl.repository.ActorRepo;
import rs.ac.bg.etf.dipl.repository.ImageRepo;
import rs.ac.bg.etf.dipl.repository.MovieRepo;
import rs.ac.bg.etf.dipl.util.ExceptionFactoryUtil;
import rs.ac.bg.etf.dipl.util.UpdateEntityUtil;

@Slf4j
@Transactional
@Service
public class MovieDAOImpl implements MovieDAO {

	@Autowired
	private MovieRepo movieRepo;
	@Autowired
	private MovieDomainMapper mapper;
	@Autowired
	private ActorDomainMapper actorMapper;
	@Autowired
	private ImageDomainMapper imageMapper;
	
	@Override
	public Page<MovieDto> getMovies(MovieSearchFilter filter) {
		log.info("Fetching all movies by filter {}", filter);
		Page<Movie> moviesPageable = movieRepo.findAllByFilter(filter);
		List<MovieDto> moviesDto = moviesPageable.getContent()
				.stream()
				.map(mapper::domainToDto)
				.collect(Collectors.toList());
		
		return PageableExecutionUtils.getPage(moviesDto, filter.getPageable(), moviesPageable::getTotalElements);
	}
	
	@Override
	public MovieDto getMovieById(Long id) {
		log.info("Fetching movie with id {}", id);
		Movie movie = movieRepo.findById(id)
				.orElseThrow(() -> ExceptionFactoryUtil.movieNotFound(id));
		return mapper.domainToDto(movie);
	}

	@Override
	public MovieDto addMovie(CreateMovieDto movie) {
		log.info("Saving movie {}.", movie.getTitle());
		Movie domain = mapper.dtoToDomain(movie);
		
		Movie savedMovie = movieRepo.save(domain);
		return mapper.domainToDto(savedMovie);
	}

	@Override
	public void updateMovie(Long id, UpdateMovieDto updateMovieDto, List<ActorDto> actors) {
		log.info("Updating movie with id {} with payload: {}", id, updateMovieDto);
		Movie movie = movieRepo.findById(id)
				.orElseThrow(() -> ExceptionFactoryUtil.movieNotFound(id));
		
		List<Actor> actorsDomain = actors.stream()
				.map(actorMapper::dtoToDomain)
				.collect(Collectors.toList());
		
		UpdateEntityUtil.updateMovie(movie, updateMovieDto, actorsDomain);
		movieRepo.save(movie);
	}

	@Override
	public void deleteMovie(Long id) {
		log.info("Deleting movie with id {}", id);
		movieRepo.deleteById(id);
	}
}
