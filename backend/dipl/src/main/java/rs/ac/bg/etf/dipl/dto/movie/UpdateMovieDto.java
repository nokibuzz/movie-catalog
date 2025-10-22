package rs.ac.bg.etf.dipl.dto.movie;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMovieDto {

	private Long id;
	private String title;
	private String description;
	private Integer duration;
	private String year;
	private List<Long> actors;
}
