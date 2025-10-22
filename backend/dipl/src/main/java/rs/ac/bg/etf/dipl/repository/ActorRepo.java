package rs.ac.bg.etf.dipl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.bg.etf.dipl.domain.Actor;

@Repository
public interface ActorRepo extends JpaRepository<Actor, Long>, ActorRepoCustom {

	List<Actor> findByIdIn(List<Long> ids);
}
