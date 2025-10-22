package rs.ac.bg.etf.dipl.mapper.impl;

import org.springframework.stereotype.Component;

import rs.ac.bg.etf.dipl.dto.image.ImageDto;
import rs.ac.bg.etf.dipl.mapper.api.ImageApiMapper;
import rs.ac.bg.etf.dipl.web.model.image.ImageResponse;

@Component
public class ImageApiMapperImpl implements ImageApiMapper {
	
	@Override
	public ImageResponse dtoToResponse(ImageDto image) {
		if (image == null) {
			return null;
		}
		return ImageResponse.builder()
				.id(image.getId())
				.name(image.getName())
				.type(image.getType())
				.data(image.getData())
				.build();
	}
}
