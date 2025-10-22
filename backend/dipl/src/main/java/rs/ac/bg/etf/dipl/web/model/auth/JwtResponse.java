package rs.ac.bg.etf.dipl.web.model.auth;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {
	
	// bearer
	private String token;
	private Long id;
	private String username;
	private String email;
	private String avatarURL;
	private List<String> roles;
}
