package rs.ac.bg.etf.dipl.mapper;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import rs.ac.bg.etf.dipl.enumeration.Genre;
import rs.ac.bg.etf.dipl.filter.ActorSearchFilter;
import rs.ac.bg.etf.dipl.filter.MovieSearchFilter;
import rs.ac.bg.etf.dipl.filter.UserSearchFilter;

@Component
public class FilterMapper {

	public static MovieSearchFilter toSearchFilter(String title, Genre genre, Pageable pageable) {
		return MovieSearchFilter.builder()
				.title(title)
				.genre(genre != null ? genre.name() : null)
				.pageable(pageable)
				.build();
	}
	
	public static ActorSearchFilter toSearchActorFilter(String name, Pageable pageable) {
		return ActorSearchFilter.builder()
				.name(name)
				.pageable(pageable)
				.build();
	}
	
	public static UserSearchFilter toSearchUserFilter(String username, Pageable pageable) {
		return UserSearchFilter.builder()
				.username(username)
				.pageable(pageable)
				.build();
	}
}
