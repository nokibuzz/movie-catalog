package rs.ac.bg.etf.dipl.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rs.ac.bg.etf.dipl.dao.ImageDAO;
import rs.ac.bg.etf.dipl.dto.image.CreateImageDto;
import rs.ac.bg.etf.dipl.dto.image.ImageDto;
import rs.ac.bg.etf.dipl.facade.ImageFacade;
import rs.ac.bg.etf.dipl.mapper.api.ImageApiMapper;
import rs.ac.bg.etf.dipl.web.model.image.ImageResponse;

@Slf4j
@Service
public class ImageFacadeImpl implements ImageFacade {

	@Autowired
	private ImageDAO imageDAO;
	@Autowired
	private ImageApiMapper mapper;
	
	@Override
	public ImageResponse upload(CreateImageDto image) {
		
		log.info("Uploading image: {}", image.getName());
		ImageDto imageDto = imageDAO.upload(image);
		return mapper.dtoToResponse(imageDto);
	}
}
