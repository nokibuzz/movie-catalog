package rs.ac.bg.etf.dipl.web.api;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import rs.ac.bg.etf.dipl.web.model.actor.ActorRequest;
import rs.ac.bg.etf.dipl.web.model.actor.ActorResponse;

@Api(value = "Actor API", description = "WEB API for managing actors")
@RequestMapping("v1/actor")
public interface ActorApi {
	
	@ApiOperation("Save actor")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Actor successfully created"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 500, message = "Internal server error"),
	})
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
				 produces = {MediaType.APPLICATION_JSON_VALUE})
	ResponseEntity<ActorResponse> saveActor(@ApiParam(value = "Create actor request") @Valid @RequestBody ActorRequest actor);
}
