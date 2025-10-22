package rs.ac.bg.etf.dipl.dto.actor;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.etf.dipl.dto.movie.MovieDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorDto {
	
	private Long id;
	private String name;
	private Date birthday;
	private String roles;
	private List<MovieDto> movies;
}
