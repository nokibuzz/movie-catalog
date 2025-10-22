package rs.ac.bg.etf.dipl.util;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import rs.ac.bg.etf.dipl.dto.auth.CreateUserDto;
import rs.ac.bg.etf.dipl.dto.auth.UserDto;
import rs.ac.bg.etf.dipl.dto.image.CreateImageDto;
import rs.ac.bg.etf.dipl.exception.BadRequestException;

@Slf4j
@Component
public final class ObjectFactoryUtil {

	public static final String ADMIN = "admin";
	public static final String USER = "user";
	
	public static CreateUserDto createUser(String name, String surname, String username, String email, String password, int avatarID) {
		
		log.info("Creating user with username: {} and email: {}", username, email);
		return CreateUserDto.builder()
				.name(name)
				.surname(surname)
				.username(username)
				.email(email)
				.password(password)
				.avatarID(avatarID)
				.build();
	}
	
	public static CreateImageDto createImage(MultipartFile image) {
		
		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		
		log.info("Creating image {} from file", fileName);
		
		if (fileName.contains("..")) {
			throw new BadRequestException("Uploaded image " + fileName + " contains double dots!");
		}
		
		CreateImageDto createImageDto = null;
		
		try {
			createImageDto = CreateImageDto.builder()
					.name(fileName)
					.type(image.getContentType().substring(6, image.getContentType().length()))
					.data(image.getBytes())
					.size(image.getSize())
					.build();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return createImageDto;
	}
}
