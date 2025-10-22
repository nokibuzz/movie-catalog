package rs.ac.bg.etf.dipl.web.api;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import rs.ac.bg.etf.dipl.web.model.actor.ActorRequest;
import rs.ac.bg.etf.dipl.web.model.actor.ActorResponse;
import rs.ac.bg.etf.dipl.web.model.movie.MovieRequest;
import rs.ac.bg.etf.dipl.web.model.movie.MovieResponse;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "Admin Actor API", description = "WEB API for managing actors as admin")
@RequestMapping("v1/admin/actor")
public interface AdminActorApi {

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation("Get list of actors")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Actors successfully fetched"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 500, message = "Internal server error"),
	})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string",
                paramType = "query"),
        @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string",
                paramType = "query")
	})
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<Page<ActorResponse>> getActorsByParams(
			@ApiParam(name = "name", value = "Actor name") @RequestParam(value = "name", required = false) String name, @ApiIgnore Pageable pageable
	);
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation("Get actor by id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Actor successfully fetched"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 500, message = "Internal server error"),
	})
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<ActorResponse> getActorById(@PathVariable("id") Long id);
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation("Save actor")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Actor successfully created"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 500, message = "Internal server error"),
	})
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization jwt token", dataType = "string",
	    		paramType = "header")
    })
	@ResponseStatus(HttpStatus.CREATED)
	ResponseEntity<ActorResponse> save(@ApiParam(value = "Save actor request") @Valid @RequestBody ActorRequest actor);
}
