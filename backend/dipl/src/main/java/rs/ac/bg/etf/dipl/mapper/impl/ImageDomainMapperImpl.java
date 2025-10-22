package rs.ac.bg.etf.dipl.mapper.impl;

import org.springframework.stereotype.Component;

import rs.ac.bg.etf.dipl.domain.Image;
import rs.ac.bg.etf.dipl.dto.image.CreateImageDto;
import rs.ac.bg.etf.dipl.dto.image.ImageDto;
import rs.ac.bg.etf.dipl.mapper.domain.ImageDomainMapper;

@Component
public class ImageDomainMapperImpl implements ImageDomainMapper {

	@Override
	public ImageDto domainToDto(Image image) {
		if (image == null) {
			return null;
		}
		return ImageDto.builder()
				.id(image.getId())
				.name(image.getFileName())
				.type(image.getFileType())
				.data(image.getData())
				.build();
	}
	
	@Override
	public Image createDtoToDomain(CreateImageDto image) {
		return Image.builder()
				.fileName(image.getName())
				.fileType(image.getType())
				.data(image.getData())
				.build();	
	}

	@Override
	public Image dtoToDomain(ImageDto imageDto) {
		if (imageDto == null) {
			return null;
		}
		return Image.builder()
				.id(imageDto.getId())
				.fileName(imageDto.getName())
				.fileType(imageDto.getType())
				.data(imageDto.getData())
				.build();
	}
}
