package rs.ac.bg.etf.dipl.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import rs.ac.bg.etf.dipl.dto.image.CreateImageDto;
import rs.ac.bg.etf.dipl.facade.ImageFacade;
import rs.ac.bg.etf.dipl.util.ObjectFactoryUtil;
import rs.ac.bg.etf.dipl.web.api.ImageApi;
import rs.ac.bg.etf.dipl.web.model.image.ImageResponse;

@RestController
public class ImageApiController implements ImageApi {

	@Autowired
	private ImageFacade imageFacade;
	
	@Override
	public ResponseEntity<ImageResponse> upload(@RequestParam(value = "image") MultipartFile image) {

		CreateImageDto createImageDto = ObjectFactoryUtil.createImage(image);
		
		return ResponseEntity.ok(imageFacade.upload(createImageDto));
	}
}
