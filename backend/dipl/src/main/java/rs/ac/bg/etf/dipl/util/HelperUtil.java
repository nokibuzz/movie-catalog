package rs.ac.bg.etf.dipl.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import rs.ac.bg.etf.dipl.enumeration.Genre;

@Component
public class HelperUtil {
	
	

	public static String convertListOfGenresToString(List<Genre> list) {

		String genres = "";
		if (list != null) {
			for (Genre genre : list) {
				genres += genre.name() + ",";
			}
			genres.substring(0, genres.length() - 1);
		} else {
			return null;
		}

		return genres;
	}

	public static List<Genre> convertStringToListOfGenres(String genres) {

		return Stream.of(genres.split(","))
				.map(Genre::valueOf)
				.collect(Collectors.toList());
	}
}
