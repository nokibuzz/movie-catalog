package rs.ac.bg.etf.dipl.mapper.api;

import rs.ac.bg.etf.dipl.dto.auth.UserDto;
import rs.ac.bg.etf.dipl.web.model.user.UserResponse;

public interface AuthApiMapper {

	UserResponse dtoToApi(UserDto user);
}
