package rs.ac.bg.etf.dipl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.bg.etf.dipl.domain.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>, UserRepoCustom {
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
