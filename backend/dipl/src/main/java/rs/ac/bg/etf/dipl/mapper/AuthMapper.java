package rs.ac.bg.etf.dipl.mapper;

import rs.ac.bg.etf.dipl.domain.User;
import rs.ac.bg.etf.dipl.dto.auth.UserDto;

public interface AuthMapper {

	User userDtoToDomain(UserDto user);

	UserDto userDomainToDto(User user);
}
