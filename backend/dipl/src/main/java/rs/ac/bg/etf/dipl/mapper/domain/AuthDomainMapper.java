package rs.ac.bg.etf.dipl.mapper.domain;

import rs.ac.bg.etf.dipl.domain.User;
import rs.ac.bg.etf.dipl.dto.auth.CreateUserDto;
import rs.ac.bg.etf.dipl.dto.auth.UserDto;

public interface AuthDomainMapper {

	UserDto domainToDto(User user);
	
	User dtoToDomain(CreateUserDto user);
}
