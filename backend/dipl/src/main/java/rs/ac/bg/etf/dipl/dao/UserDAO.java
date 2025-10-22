package rs.ac.bg.etf.dipl.dao;

import org.springframework.data.domain.Page;

import rs.ac.bg.etf.dipl.dto.auth.UserDto;
import rs.ac.bg.etf.dipl.filter.UserSearchFilter;

public interface UserDAO {

	Page<UserDto> getUsers(UserSearchFilter filter);
	
	UserDto getUser(String username);
	
	String getAvatarURLByUsername(String username);
	
	void banUser(String username, boolean toBan);
	
	Boolean isBanned(String username);
}
