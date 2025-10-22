package rs.ac.bg.etf.dipl.mapper.impl;

import org.springframework.stereotype.Component;

import rs.ac.bg.etf.dipl.dto.auth.UserDto;
import rs.ac.bg.etf.dipl.mapper.api.AuthApiMapper;
import rs.ac.bg.etf.dipl.web.model.user.UserResponse;

@Component
public class AuthApiMapperImpl implements AuthApiMapper {

	@Override
	public UserResponse dtoToApi(UserDto user) {
		return UserResponse.builder()
				.id(user.getId())
				.name(user.getName() + " " + user.getSurname())
				.username(user.getUsername())
				.email(user.getEmail())
				.avatarURL("/static/avatars/" + user.getAvatarID() + ".png")
				.banned(user.isBanned())
				.build();
				
	}
}
