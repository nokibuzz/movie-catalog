package rs.ac.bg.etf.dipl.facade;

import rs.ac.bg.etf.dipl.web.model.auth.JwtResponse;
import rs.ac.bg.etf.dipl.web.model.auth.LoginRequest;
import rs.ac.bg.etf.dipl.web.model.auth.SignupRequest;
import rs.ac.bg.etf.dipl.web.model.user.UserResponse;

import org.springframework.data.domain.Page;

import rs.ac.bg.etf.dipl.filter.UserSearchFilter;
import rs.ac.bg.etf.dipl.web.model.auth.AuthResponse;

public interface UserFacade {
	
	Page<UserResponse> getUsers(UserSearchFilter filter);
	
	UserResponse getUser(String username);

	JwtResponse authenticateUser(LoginRequest loginRequest);

	AuthResponse registerUser(SignupRequest signUpRequest);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	void banUser(String username, boolean toBan);
	
	Boolean isBanned(String username);
}
