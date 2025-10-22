package rs.ac.bg.etf.dipl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.bg.etf.dipl.domain.Role;
import rs.ac.bg.etf.dipl.enumeration.RoleConst;

public interface RoleRepo extends JpaRepository<Role, Long> {

	Optional<Role> findByName(RoleConst name);
}
