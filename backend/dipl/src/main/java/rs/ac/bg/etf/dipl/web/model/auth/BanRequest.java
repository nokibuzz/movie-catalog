package rs.ac.bg.etf.dipl.web.model.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BanRequest {

	private String username;
	private boolean banOrUnban;
}
