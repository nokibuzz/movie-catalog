package rs.ac.bg.etf.dipl.mapper.domain;

import rs.ac.bg.etf.dipl.domain.Image;
import rs.ac.bg.etf.dipl.dto.image.CreateImageDto;
import rs.ac.bg.etf.dipl.dto.image.ImageDto;

public interface ImageDomainMapper {

	ImageDto domainToDto(Image image);
	
	Image createDtoToDomain(CreateImageDto image);

	Image dtoToDomain(ImageDto imageDto);
}
