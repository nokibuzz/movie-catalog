package rs.ac.bg.etf.dipl.filter;

import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieSearchFilter {

	private String title;
	private String genre;
	private Pageable pageable;
}
