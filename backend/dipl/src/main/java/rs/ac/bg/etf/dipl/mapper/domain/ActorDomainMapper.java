package rs.ac.bg.etf.dipl.mapper.domain;

import rs.ac.bg.etf.dipl.domain.Actor;
import rs.ac.bg.etf.dipl.dto.actor.ActorDto;
import rs.ac.bg.etf.dipl.dto.actor.CreateActorDto;

public interface ActorDomainMapper {

	Actor dtoToDomain(CreateActorDto actor);
	
	Actor dtoToDomain(ActorDto actor);
	
	ActorDto domainToDto(Actor actor);
}
