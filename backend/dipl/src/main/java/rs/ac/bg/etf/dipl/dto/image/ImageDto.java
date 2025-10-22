package rs.ac.bg.etf.dipl.dto.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDto {

	private long id;
	private String name;
	private String type;
	private byte[] data;
}
