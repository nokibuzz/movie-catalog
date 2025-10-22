package rs.ac.bg.etf.dipl.web.model.movie;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMovieRequest {

	private String title;
	private String description;
	private int duration;
	private String year;
	private List<Long> actors;
}
