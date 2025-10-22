package rs.ac.bg.etf.dipl.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.bg.etf.dipl.facade.UserFacade;
import rs.ac.bg.etf.dipl.util.ExceptionFactoryUtil;
import rs.ac.bg.etf.dipl.web.api.UserApi;
import rs.ac.bg.etf.dipl.web.model.auth.JwtResponse;
import rs.ac.bg.etf.dipl.web.model.auth.LoginRequest;
import rs.ac.bg.etf.dipl.web.model.auth.SignupRequest;
import rs.ac.bg.etf.dipl.web.model.auth.AuthResponse;

@RestController
public class UserApiController implements UserApi {

	@Autowired
	private UserFacade userFacade;
	
	@Override
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		// error if username is already taken
		if(!userFacade.existsByUsername(loginRequest.getUsername())) {
            throw ExceptionFactoryUtil.usernameNotExists(loginRequest.getUsername());
        }
		if(userFacade.isBanned(loginRequest.getUsername())) {
            throw ExceptionFactoryUtil.usernameBanned(loginRequest.getUsername());
        }
		return ResponseEntity.ok(userFacade.authenticateUser(loginRequest));
	}

	@Override
	public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		
		// error if username is already taken
		if(userFacade.existsByUsername(signUpRequest.getUsername())) {
            return ExceptionFactoryUtil.usernameTaken();
        }
		
		return new ResponseEntity<>(userFacade.registerUser(signUpRequest), HttpStatus.CREATED);
	}
	
}
