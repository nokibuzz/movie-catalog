package rs.ac.bg.etf.dipl.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rs.ac.bg.etf.dipl.dto.actor.ActorDto;
import rs.ac.bg.etf.dipl.dto.image.ImageDto;
import rs.ac.bg.etf.dipl.dto.movie.CreateMovieDto;
import rs.ac.bg.etf.dipl.dto.movie.MovieDto;
import rs.ac.bg.etf.dipl.dto.movie.UpdateMovieDto;
import rs.ac.bg.etf.dipl.mapper.api.ActorApiMapper;
import rs.ac.bg.etf.dipl.mapper.api.ImageApiMapper;
import rs.ac.bg.etf.dipl.mapper.api.MovieApiMapper;
import rs.ac.bg.etf.dipl.web.model.actor.ActorResponse;
import rs.ac.bg.etf.dipl.web.model.movie.MovieRequest;
import rs.ac.bg.etf.dipl.web.model.movie.MovieResponse;
import rs.ac.bg.etf.dipl.web.model.movie.UpdateMovieRequest;

@Component
public class MovieApiMapperImpl implements MovieApiMapper {

	@Autowired
	private ImageApiMapper imageMapper;
	@Autowired
	private ActorApiMapper actorMapper;
	
	@Override
	public CreateMovieDto apiToDto(MovieRequest movie) {
		return CreateMovieDto.builder()
				.title(movie.getTitle())
				.description(movie.getDescription())
				.duration(movie.getDuration())
				.year(movie.getYear())
				.trailer(movie.getTrailerUrl())
				.ranking(movie.getRanking())
				.genres(movie.getGenres())
				.build();
	}

	@Override
	public MovieResponse dtoToApi(MovieDto movie) {
		return MovieResponse.builder()
				.id(movie.getId())
				.title(movie.getTitle())
				.description(movie.getDescription())
				.duration(movie.getDuration())
				.year(movie.getYear())
				.trailerUrl(movie.getTrailer())
				.ranking(movie.getRanking())
				.genres(movie.getGenres())
				.image(imageMapper.dtoToResponse(movie.getImage()))
				.actors(actorResponse(movie.getActors()))
				.build();
	}
	
	private List<ActorResponse> actorResponse(List<ActorDto> actorDtos) {
		if (actorDtos == null) return null;
		return actorDtos.stream()
				.map(actor -> actorMapper.dtoToApi(actor))
				.collect(Collectors.toList());
	}

	@Override
	public UpdateMovieDto updateMovieRequestToDto(UpdateMovieRequest movie) {
		return UpdateMovieDto.builder()
				.title(movie.getTitle())
				.description(movie.getDescription())
				.duration(movie.getDuration())
				.year(movie.getYear())
				.actors(movie.getActors())
				.build();
	}
}
