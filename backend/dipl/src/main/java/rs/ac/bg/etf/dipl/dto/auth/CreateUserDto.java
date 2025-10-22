package rs.ac.bg.etf.dipl.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {

	private String name;
	private String surname;
	private String username;
	private String email;
	private String password;
	private int avatarID;
}
