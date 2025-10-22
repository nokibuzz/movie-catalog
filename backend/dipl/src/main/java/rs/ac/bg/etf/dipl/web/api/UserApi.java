package rs.ac.bg.etf.dipl.web.api;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import rs.ac.bg.etf.dipl.web.model.auth.JwtResponse;
import rs.ac.bg.etf.dipl.web.model.auth.LoginRequest;
import rs.ac.bg.etf.dipl.web.model.auth.SignupRequest;
import rs.ac.bg.etf.dipl.web.model.auth.AuthResponse;

@Api(value = "Authentication and Registration API", description = "WEB API for managing user authentication and authorization")
@RequestMapping("v1/user")
public interface UserApi {
	
	@PostMapping(path = "/signin", 
			produces = {MediaType.APPLICATION_JSON_VALUE}, 
			consumes = {MediaType.APPLICATION_JSON_VALUE}
	)
	@ApiOperation("LogIn operation")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Logged in successfully"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 500, message = "Internal server error"),
	})
	ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest);
	
	@PostMapping(path = "/signup", 
			produces = {MediaType.APPLICATION_JSON_VALUE}, 
			consumes = {MediaType.APPLICATION_JSON_VALUE}
	)
	@ApiOperation("Sign Up operation")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Signed up successfully"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 500, message = "Internal server error"),
	})
	ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest);
}
