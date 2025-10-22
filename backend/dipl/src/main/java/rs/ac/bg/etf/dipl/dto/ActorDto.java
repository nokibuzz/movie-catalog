package rs.ac.bg.etf.dipl.dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.etf.dipl.dto.image.ImageDto;
import rs.ac.bg.etf.dipl.dto.movie.MovieDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorDto {
	
	private Long id;
	private String name;
	private Instant birthday;
	private String activeFrom;
	private ImageDto image;
	private List<MovieDto> movies;
}
