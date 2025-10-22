package rs.ac.bg.etf.dipl.mapper.impl;

import org.springframework.stereotype.Component;

import rs.ac.bg.etf.dipl.domain.User;
import rs.ac.bg.etf.dipl.dto.auth.UserDto;
import rs.ac.bg.etf.dipl.mapper.AuthMapper;

@Component
public class AuthMapperImpl implements AuthMapper {

	@Override
	public User userDtoToDomain(UserDto user) {
		return User.builder()
				.username(user.getUsername())
				.email(user.getEmail())
				.password(user.getPassword())
				.build();
	}

	@Override
	public UserDto userDomainToDto(User user) {
		return UserDto.builder()
				.username(user.getUsername())
				.email(user.getEmail())
				.password(user.getPassword())
				.build();
	}

}
