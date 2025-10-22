package rs.ac.bg.etf.dipl.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rs.ac.bg.etf.dipl.domain.Actor;
import rs.ac.bg.etf.dipl.domain.Movie;
import rs.ac.bg.etf.dipl.dto.actor.ActorDto;
import rs.ac.bg.etf.dipl.dto.movie.CreateMovieDto;
import rs.ac.bg.etf.dipl.dto.movie.MovieDto;
import rs.ac.bg.etf.dipl.mapper.domain.ActorDomainMapper;
import rs.ac.bg.etf.dipl.mapper.domain.ImageDomainMapper;
import rs.ac.bg.etf.dipl.mapper.domain.MovieDomainMapper;
import rs.ac.bg.etf.dipl.util.HelperUtil;

@Component
public class MovieDomainMapperImpl implements MovieDomainMapper {

	@Autowired
	private ImageDomainMapper imageMapper;
	@Autowired
	private ActorDomainMapper actorMapper;
	
	@Override
	public Movie dtoToDomain(CreateMovieDto movie) {
		return Movie.builder()
				.title(movie.getTitle())
				.description(movie.getDescription())
				.duration(movie.getDuration())
				.year(movie.getYear())
				.trailerUrl(movie.getTrailer())
				.ranking(movie.getRanking())
				.genres(HelperUtil.convertListOfGenresToString(movie.getGenres()))
				.image(imageMapper.dtoToDomain(movie.getImage()))
				.build();
	}

	@Override
	public MovieDto domainToDto(Movie movie) {
		return MovieDto.builder()
				.id(movie.getId())
				.title(movie.getTitle())
				.description(movie.getDescription())
				.duration(movie.getDuration())
				.year(movie.getYear())
				.trailer(movie.getTrailerUrl())
				.ranking(movie.getRanking())
				.genres(HelperUtil.convertStringToListOfGenres(movie.getGenres()))
				.image(imageMapper.domainToDto(movie.getImage()))
				.actors(dtoActors(movie.getActors()))
				.build();
	}

	private List<Actor> domainActors(List<ActorDto> actorDtos) {
		return actorDtos.stream()
				.map(actor -> actorMapper.dtoToDomain(actor))
				.collect(Collectors.toList());
	}
	
	private List<ActorDto> dtoActors(List<Actor> actors) {
		if (actors == null) return null;
		return actors.stream()
				.map(actor -> actorMapper.domainToDto(actor))
				.collect(Collectors.toList());
	}
}
