package rs.ac.bg.etf.dipl.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import rs.ac.bg.etf.dipl.dto.actor.ActorDto;
import rs.ac.bg.etf.dipl.dto.actor.CreateActorDto;
import rs.ac.bg.etf.dipl.filter.ActorSearchFilter;

public interface ActorDAO {

	Page<ActorDto> getActors(ActorSearchFilter filter);
	
	ActorDto getActorById(Long id);
	
	ActorDto addActor(CreateActorDto actor);

	List<ActorDto> findActorsById(List<Long> actors);
}
