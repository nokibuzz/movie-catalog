package rs.ac.bg.etf.dipl.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.bg.etf.dipl.facade.ActorFacade;
import rs.ac.bg.etf.dipl.mapper.FilterMapper;
import rs.ac.bg.etf.dipl.web.api.AdminActorApi;
import rs.ac.bg.etf.dipl.web.model.actor.ActorRequest;
import rs.ac.bg.etf.dipl.web.model.actor.ActorResponse;

@RestController
public class AdminActorApiController implements AdminActorApi {

	@Autowired
	private ActorFacade actorFacade;
	
	@Override
	public ResponseEntity<Page<ActorResponse>> getActorsByParams(String name, Pageable pageable) {
		return ResponseEntity.ok(actorFacade.getActorsByParams(FilterMapper.toSearchActorFilter(name, pageable)));
	}

	@Override
	public ResponseEntity<ActorResponse> getActorById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(actorFacade.getActorById(id));
	}

	@Override
	public ResponseEntity<ActorResponse> save(@Valid ActorRequest actor) {
		return new ResponseEntity<>(actorFacade.addActor(actor), HttpStatus.CREATED);
	}
}
