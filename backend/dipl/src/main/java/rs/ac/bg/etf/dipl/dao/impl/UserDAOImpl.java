package rs.ac.bg.etf.dipl.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rs.ac.bg.etf.dipl.dao.UserDAO;
import rs.ac.bg.etf.dipl.domain.User;
import rs.ac.bg.etf.dipl.dto.auth.UserDto;
import rs.ac.bg.etf.dipl.filter.UserSearchFilter;
import rs.ac.bg.etf.dipl.mapper.domain.AuthDomainMapper;
import rs.ac.bg.etf.dipl.repository.UserRepo;
import rs.ac.bg.etf.dipl.util.ExceptionFactoryUtil;

@Slf4j
@Service
public class UserDAOImpl implements UserDAO {

	private static final String ROLE_ADMIN = "[Role(id=2, name=ROLE_ADMIN)]";
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AuthDomainMapper mapper;

	@Override
	public Page<UserDto> getUsers(UserSearchFilter filter) {
		log.info("Fetching users by filter {}", filter);
		Page<User> usersPageable = userRepo.findAllByFilter(filter);
		List<UserDto> usersDto = usersPageable.getContent()
				.stream()
				.filter(usr -> !ROLE_ADMIN.equals(usr.getRoles().toString()))
				.map(mapper::domainToDto)
				.collect(Collectors.toList());
				
		return PageableExecutionUtils.getPage(usersDto, filter.getPageable(), usersPageable::getTotalElements);
	}

	@Override
	public UserDto getUser(String username) {
		log.info("Fetching user with username {}", username);
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> ExceptionFactoryUtil.usernameNotExists(username));
		
		return mapper.domainToDto(user);
	}
	
	@Override
	public String getAvatarURLByUsername(String username) {
		log.info("Getting avatar id for user with username {}.", username);
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> ExceptionFactoryUtil.userNotExists(username));
		
		return "/static/avatars/" + user.getAvatarID() + ".png";
	}

	@Override
	public void banUser(String username, boolean toBan) {
		log.info(toBan ? "Banning " : "Unbanning " + "user with username {}.", username);
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> ExceptionFactoryUtil.userNotExists(username));
		
		user.setBanned(toBan);
		userRepo.save(user);
	}

	@Override
	public Boolean isBanned(String username) {
		log.info("Checking if user with username {} is banned.", username);
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> ExceptionFactoryUtil.userNotExists(username));
		return user.isBanned();
	}
}
