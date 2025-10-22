package rs.ac.bg.etf.dipl.facade.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import rs.ac.bg.etf.dipl.dao.ActorDAO;
import rs.ac.bg.etf.dipl.dao.ImageDAO;
import rs.ac.bg.etf.dipl.dao.MovieDAO;
import rs.ac.bg.etf.dipl.dto.actor.ActorDto;
import rs.ac.bg.etf.dipl.dto.image.ImageDto;
import rs.ac.bg.etf.dipl.dto.movie.CreateMovieDto;
import rs.ac.bg.etf.dipl.dto.movie.MovieDto;
import rs.ac.bg.etf.dipl.facade.MovieFacade;
import rs.ac.bg.etf.dipl.filter.MovieSearchFilter;
import rs.ac.bg.etf.dipl.mapper.api.MovieApiMapper;
import rs.ac.bg.etf.dipl.web.model.movie.MovieRequest;
import rs.ac.bg.etf.dipl.web.model.movie.MovieResponse;
import rs.ac.bg.etf.dipl.web.model.movie.UpdateMovieRequest;

@Service
public class MovieFacadeImpl implements MovieFacade {

	@Autowired
	private MovieDAO movieDAO;
	@Autowired
	private ActorDAO actorDAO;
	@Autowired
	private ImageDAO imageDAO;
	@Autowired
	private MovieApiMapper mapper;
	
	@Override
	public Page<MovieResponse> getMoviesByParams(MovieSearchFilter filter) {
		Page<MovieDto> moviesDto =  movieDAO.getMovies(filter);
		
		return PageableExecutionUtils.getPage(moviesDto.stream().map(mapper::dtoToApi).collect(Collectors.toList()),
				filter.getPageable(),
				moviesDto::getTotalElements);
	}
	
	@Override
	public MovieResponse getMovieById(Long id) {
		MovieDto movieDto = movieDAO.getMovieById(id);
		return mapper.dtoToApi(movieDto);
	}

	@Override
	public MovieResponse addMovie(MovieRequest movie) {
		ImageDto imageDto = imageDAO.findImageById(movie.getImageID());
		
		CreateMovieDto dto = mapper.apiToDto(movie);
		dto.setImage(imageDto);
		return mapper.dtoToApi(movieDAO.addMovie(dto));
	}

	@Override
	public void updateMovie(Long id, UpdateMovieRequest movie) {
		movieDAO.updateMovie(id, mapper.updateMovieRequestToDto(movie), actorDAO.findActorsById(movie.getActors()));
	}

	@Override
	public void deleteMovie(Long id) {
		movieDAO.deleteMovie(id);
	}
}
