package rs.ac.bg.etf.dipl.dao;

import rs.ac.bg.etf.dipl.dto.image.CreateImageDto;
import rs.ac.bg.etf.dipl.dto.image.ImageDto;

public interface ImageDAO {

	ImageDto upload(CreateImageDto image);

	ImageDto findImageById(Long id);
}
