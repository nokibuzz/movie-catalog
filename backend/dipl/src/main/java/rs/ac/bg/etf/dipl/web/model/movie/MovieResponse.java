package rs.ac.bg.etf.dipl.web.model.movie;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.etf.dipl.enumeration.Genre;
import rs.ac.bg.etf.dipl.web.model.actor.ActorResponse;
import rs.ac.bg.etf.dipl.web.model.image.ImageResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieResponse {

	private Long id;
	private String title;
	private String trailerUrl;
	private int duration;
	private String description;
	private String year;
	private String ranking;
	
	private ImageResponse image;
	
	private List<Genre> genres;
	// make classes for this
	private List<String> crew;
	private List<ActorResponse> actors;
}
