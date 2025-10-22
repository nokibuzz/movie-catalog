package rs.ac.bg.etf.dipl.util;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import rs.ac.bg.etf.dipl.dto.error.SubErrorDto;
import rs.ac.bg.etf.dipl.exception.ResourceNotFoundException;
import rs.ac.bg.etf.dipl.web.model.auth.AuthResponse;


@Component
public class ExceptionFactoryUtil {

	public static ResourceNotFoundException roleNotFound(String role) {
		return new ResourceNotFoundException("Role " + role + "doesn't exists!");
	}

	public static ResponseEntity<AuthResponse> usernameTaken() {
		return ResponseEntity
        		.badRequest()
        		.body(AuthResponse.builder()
        				.message("Username is already taken!")
        				.ok(false)
        			.build()
        		);
	}
	
	public static ResourceNotFoundException usernameNotExists(String username) {
		return new ResourceNotFoundException("Username " + username + " don't exists in the system!");
	}
	
	public static ResourceNotFoundException userNotExists(String username) {
		return new ResourceNotFoundException("User with username " + username + " don't exists in the system!");
	}
	
	public static ResourceNotFoundException usernameBanned(String username) {
		return new ResourceNotFoundException("User with username " + username + " is banned from the system!");
	}
	
	public static ResourceNotFoundException movieNotFound(Long movieId) {
        SubErrorDto subError = SubErrorDto.builder()
                .object("Movie")
                .field("id")
                .message(String.format("Movie with id: %d does not exist", movieId))
                .build();
        return new ResourceNotFoundException(subError);
    }
	
	public static ResourceNotFoundException actorNotFound(Long actorId) {
        SubErrorDto subError = SubErrorDto.builder()
                .object("Actor")
                .field("id")
                .message(String.format("Actor with id: %d does not exist", actorId))
                .build();
        return new ResourceNotFoundException(subError);
    }
}
