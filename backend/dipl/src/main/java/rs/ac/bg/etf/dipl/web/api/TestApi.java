package rs.ac.bg.etf.dipl.web.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/test")
public interface TestApi {

	@GetMapping("/all")
	String allAccess();
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	String userAccess();

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	String adminAccess();
}
