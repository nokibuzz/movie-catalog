package rs.ac.bg.etf.dipl.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rs.ac.bg.etf.dipl.dao.ImageDAO;
import rs.ac.bg.etf.dipl.domain.Image;
import rs.ac.bg.etf.dipl.dto.image.CreateImageDto;
import rs.ac.bg.etf.dipl.dto.image.ImageDto;
import rs.ac.bg.etf.dipl.mapper.domain.ImageDomainMapper;
import rs.ac.bg.etf.dipl.repository.ImageRepo;

@Slf4j
@Service
public class ImageDAOImpl implements ImageDAO {

	@Autowired
	private ImageRepo imageRepo;
	@Autowired
	private ImageDomainMapper mapper;
	
	@Override
	public ImageDto upload(CreateImageDto image) {
		
		Image domain = imageRepo.save(mapper.createDtoToDomain(image));
		return mapper.domainToDto(domain);
	}

	@Override
	public ImageDto findImageById(Long id) {

		log.info("Fetching image with id {}", id);
		Image domain = imageRepo.findById(id).orElse(null);
		ImageDto imageDto = null;
		
		if (domain != null) {
			imageDto = mapper.domainToDto(domain);
		} else {
			log.warn("Image with id: {} doesn't exists", id);
		}
		
		return imageDto;
	}

}
