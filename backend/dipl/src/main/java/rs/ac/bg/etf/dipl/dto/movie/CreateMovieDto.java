package rs.ac.bg.etf.dipl.dto.movie;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.etf.dipl.dto.image.ImageDto;
import rs.ac.bg.etf.dipl.enumeration.Genre;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMovieDto {

	private String title;
	private String description;
	private int duration;
	private String year;
	private String ranking;
	private String trailer;
	private ImageDto image;
	private List<Genre> genres;
}
