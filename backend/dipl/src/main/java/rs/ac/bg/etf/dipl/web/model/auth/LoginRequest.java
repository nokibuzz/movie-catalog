package rs.ac.bg.etf.dipl.web.model.auth;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
	
	@NotBlank
	private String username;

	@NotBlank
	private String password;
}
