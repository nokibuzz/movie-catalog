package rs.ac.bg.etf.dipl.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.etf.dipl.dto.image.ImageDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateActorDto {

	private String name;
	private Instant birthday;
	private String activeFrom;
	private ImageDto image;
}
