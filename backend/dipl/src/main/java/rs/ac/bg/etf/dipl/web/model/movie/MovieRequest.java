package rs.ac.bg.etf.dipl.web.model.movie;

import java.util.List;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.etf.dipl.enumeration.Genre;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieRequest {

	@NotNull
	private String title;
	private String trailerUrl;
	private int duration;
	private String description;
	private String year;
	private String ranking;
	private List<Genre> genres;
	private Long imageID;
}
