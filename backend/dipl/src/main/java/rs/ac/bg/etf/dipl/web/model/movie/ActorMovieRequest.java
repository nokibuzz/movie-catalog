package rs.ac.bg.etf.dipl.web.model.movie;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorMovieRequest {

	@NotBlank
	private Long id;
}
