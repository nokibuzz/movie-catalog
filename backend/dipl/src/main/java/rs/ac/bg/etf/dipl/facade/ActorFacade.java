package rs.ac.bg.etf.dipl.facade;

import org.springframework.data.domain.Page;

import rs.ac.bg.etf.dipl.filter.ActorSearchFilter;
import rs.ac.bg.etf.dipl.web.model.actor.ActorRequest;
import rs.ac.bg.etf.dipl.web.model.actor.ActorResponse;

public interface ActorFacade {

	Page<ActorResponse> getActorsByParams(ActorSearchFilter filter);
	
	ActorResponse getActorById(Long id);
	
	ActorResponse addActor(ActorRequest actor);
}
