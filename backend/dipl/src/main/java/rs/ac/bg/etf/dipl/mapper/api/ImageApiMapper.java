package rs.ac.bg.etf.dipl.mapper.api;

import rs.ac.bg.etf.dipl.dto.image.ImageDto;
import rs.ac.bg.etf.dipl.web.model.image.ImageResponse;

public interface ImageApiMapper {
	
	ImageResponse dtoToResponse(ImageDto image);
}
