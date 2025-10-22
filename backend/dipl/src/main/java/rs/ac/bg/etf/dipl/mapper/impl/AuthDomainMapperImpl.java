package rs.ac.bg.etf.dipl.mapper.impl;

import org.springframework.stereotype.Component;

import rs.ac.bg.etf.dipl.domain.User;
import rs.ac.bg.etf.dipl.dto.auth.CreateUserDto;
import rs.ac.bg.etf.dipl.dto.auth.UserDto;
import rs.ac.bg.etf.dipl.mapper.domain.AuthDomainMapper;

@Component
public class AuthDomainMapperImpl implements AuthDomainMapper {

	@Override
	public User dtoToDomain(CreateUserDto user) {
		return User.builder()
				.name(user.getName())
				.surname(user.getSurname())
				.username(user.getUsername())
				.email(user.getEmail())
				.password(user.getPassword())
				.avatarID(user.getAvatarID())
				.build();
	}

	@Override
	public UserDto domainToDto(User user) {
		return UserDto.builder()
				.id(user.getId())
				.name(user.getName())
				.surname(user.getSurname())
				.username(user.getUsername())
				.email(user.getEmail())
				.password(user.getPassword())
				.avatarID(user.getAvatarID())
				.banned(user.isBanned())
				.build();
	}
}
