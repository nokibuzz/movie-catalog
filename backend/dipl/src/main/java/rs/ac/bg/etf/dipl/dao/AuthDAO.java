package rs.ac.bg.etf.dipl.dao;

import java.util.Set;

import rs.ac.bg.etf.dipl.domain.Role;
import rs.ac.bg.etf.dipl.dto.auth.CreateUserDto;
import rs.ac.bg.etf.dipl.dto.auth.UserDto;
import rs.ac.bg.etf.dipl.enumeration.RoleConst;

public interface AuthDAO {

	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
	
	// special case, because we only
	// need this value to save it again
	// to database, so no mapping is 
	// needed here
	Role findByName(RoleConst role);
	
	// specific case with dto and domain object
	// but this will be user just here,
	// so it's ok
	UserDto saveUserRoles(CreateUserDto user, Set<Role> roles);
}
