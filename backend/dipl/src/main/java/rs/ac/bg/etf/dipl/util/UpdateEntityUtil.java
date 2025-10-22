package rs.ac.bg.etf.dipl.util;

import java.util.List;

import org.springframework.stereotype.Component;

import rs.ac.bg.etf.dipl.domain.Actor;
import rs.ac.bg.etf.dipl.domain.Movie;
import rs.ac.bg.etf.dipl.dto.movie.UpdateMovieDto;

@Component
public class UpdateEntityUtil {

	public static void updateMovie(Movie movie, UpdateMovieDto updateMovie, List<Actor> actors) {
		
		// title
		if (updateMovie.getTitle() != null) {
			movie.setTitle(updateMovie.getTitle());
		}
		// description
		if (updateMovie.getDescription() != null) {
			movie.setDescription(updateMovie.getDescription());
		}
		// duration
		if (updateMovie.getDuration() != null) {
			movie.setDuration(updateMovie.getDuration());
		}
		// year
		if (updateMovie.getYear() != null) {
			movie.setYear(updateMovie.getYear());
		}
		// actors
		if (actors != null) {
			movie.setActors(actors);
		}
	}
}
