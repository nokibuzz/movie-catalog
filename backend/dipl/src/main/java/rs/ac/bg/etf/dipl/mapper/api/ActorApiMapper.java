package rs.ac.bg.etf.dipl.mapper.api;

import rs.ac.bg.etf.dipl.dto.actor.ActorDto;
import rs.ac.bg.etf.dipl.dto.actor.CreateActorDto;
import rs.ac.bg.etf.dipl.web.model.actor.ActorRequest;
import rs.ac.bg.etf.dipl.web.model.actor.ActorResponse;

public interface ActorApiMapper {

	CreateActorDto apiToDto(ActorRequest actor);
	
	ActorResponse dtoToApi(ActorDto actor);
}
