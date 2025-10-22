package rs.ac.bg.etf.dipl.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.bg.etf.dipl.facade.ActorFacade;
import rs.ac.bg.etf.dipl.web.api.ActorApi;
import rs.ac.bg.etf.dipl.web.model.actor.ActorRequest;
import rs.ac.bg.etf.dipl.web.model.actor.ActorResponse;

@RestController
public class ActorApiController implements ActorApi {

	@Autowired
	private ActorFacade actorFacade;

	@Override
	public ResponseEntity<ActorResponse> saveActor(@Valid @RequestBody ActorRequest actor) {

		return new ResponseEntity<>(actorFacade.addActor(actor), HttpStatus.CREATED);
	}

}
