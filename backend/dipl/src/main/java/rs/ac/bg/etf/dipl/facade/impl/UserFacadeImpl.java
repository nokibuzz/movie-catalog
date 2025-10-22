package rs.ac.bg.etf.dipl.facade.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import rs.ac.bg.etf.dipl.dao.AuthDAO;
import rs.ac.bg.etf.dipl.dao.UserDAO;
import rs.ac.bg.etf.dipl.domain.Role;
import rs.ac.bg.etf.dipl.dto.auth.CreateUserDto;
import rs.ac.bg.etf.dipl.dto.auth.UserDto;
import rs.ac.bg.etf.dipl.enumeration.RoleConst;
import rs.ac.bg.etf.dipl.facade.UserFacade;
import rs.ac.bg.etf.dipl.filter.UserSearchFilter;
import rs.ac.bg.etf.dipl.mapper.api.AuthApiMapper;
import rs.ac.bg.etf.dipl.security.service.UserDetailsImpl;
import rs.ac.bg.etf.dipl.util.JwtUtils;
import rs.ac.bg.etf.dipl.util.ObjectFactoryUtil;
import rs.ac.bg.etf.dipl.web.model.auth.AuthResponse;
import rs.ac.bg.etf.dipl.web.model.auth.JwtResponse;
import rs.ac.bg.etf.dipl.web.model.auth.LoginRequest;
import rs.ac.bg.etf.dipl.web.model.auth.SignupRequest;
import rs.ac.bg.etf.dipl.web.model.user.UserResponse;

@Service
public class UserFacadeImpl implements UserFacade {

	@Autowired
	private AuthDAO authDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private AuthApiMapper mapper;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	public Page<UserResponse> getUsers(UserSearchFilter filter) {
		Page<UserDto> usersDto =  userDAO.getUsers(filter);
		
		return PageableExecutionUtils.getPage(usersDto.stream().map(mapper::dtoToApi).collect(Collectors.toList()),
				filter.getPageable(),
				usersDto::getTotalElements);
	}

	@Override
	public UserResponse getUser(String username) {
		return mapper.dtoToApi(userDAO.getUser(username));
	}
	
	@Override
	public JwtResponse authenticateUser(LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return JwtResponse.builder()
			.token(token) 
			.id(userDetails.getId()) 
			.username(userDetails.getUsername()) 
			.email(userDetails.getEmail())
			.avatarURL(userDAO.getAvatarURLByUsername(userDetails.getUsername()))
			.roles(roles)
		.build();
	}

	@Override
	public AuthResponse registerUser(SignupRequest signUpRequest) {

		// Create new user's account
		CreateUserDto user = ObjectFactoryUtil.createUser(
				signUpRequest.getName(),
				signUpRequest.getSurname(),
				signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getAvatarID());

		Set<String> reqRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (reqRoles == null) {
			Role userRole = authDAO.findByName(RoleConst.ROLE_USER);
			roles.add(userRole);
		} else {
			reqRoles.forEach(role -> {
				switch (role) {
				case ObjectFactoryUtil.ADMIN:
					Role adminRole = authDAO.findByName(RoleConst.ROLE_ADMIN);
					roles.add(adminRole);
					break;
				default:
					Role userRole = authDAO.findByName(RoleConst.ROLE_USER);
					roles.add(userRole);
				}
			});
		}

		authDAO.saveUserRoles(user, roles);

		return AuthResponse.builder()
				.message("User with username " + signUpRequest.getUsername() + " successfully registrated!")
				.ok(true)
			.build();
	}
	
	public Boolean existsByUsername(String username) {
		return authDAO.existsByUsername(username);
	}
	
	public Boolean existsByEmail(String email) {
		return authDAO.existsByEmail(email);
	}

	@Override
	public void banUser(String username, boolean toBan) {
		userDAO.banUser(username, toBan);
	}

	@Override
	public Boolean isBanned(String username) {
		return userDAO.isBanned(username);
	}
}
