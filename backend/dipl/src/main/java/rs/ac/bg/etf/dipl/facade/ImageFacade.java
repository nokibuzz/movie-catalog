package rs.ac.bg.etf.dipl.facade;

import rs.ac.bg.etf.dipl.dto.image.CreateImageDto;
import rs.ac.bg.etf.dipl.web.model.image.ImageResponse;

public interface ImageFacade {

	ImageResponse upload(CreateImageDto image);
}
