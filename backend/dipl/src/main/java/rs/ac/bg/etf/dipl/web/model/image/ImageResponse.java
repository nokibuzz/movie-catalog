package rs.ac.bg.etf.dipl.web.model.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponse {
	
	private long id;
	private String name;
    private String type;
    private byte[] data;
}
