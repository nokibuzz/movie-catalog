package rs.ac.bg.etf.dipl.web.model.actor;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.etf.dipl.web.model.image.ImageResponse;
import rs.ac.bg.etf.dipl.web.model.movie.MovieResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorResponse {

	private Long id;
	private String name;
	private Date birthday;
	private String roles;
	private List<MovieResponse> movies;
}
