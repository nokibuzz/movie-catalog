package rs.ac.bg.etf.dipl.web.api;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import rs.ac.bg.etf.dipl.enumeration.Genre;
import rs.ac.bg.etf.dipl.web.model.movie.MovieRequest;
import rs.ac.bg.etf.dipl.web.model.movie.MovieResponse;
import rs.ac.bg.etf.dipl.web.model.movie.UpdateMovieRequest;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "Admin Movie API", description = "WEB API for managing movies as admin")
@RequestMapping("v1/admin/movie")
public interface AdminMovieApi {

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation("Get list of movies")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Movies successfully fetched"),
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
	ResponseEntity<Page<MovieResponse>> getMoviesByParams(
			@ApiParam(name = "title", value = "Movie Title")
			@RequestParam(value = "title", required = false) String title,
			@ApiParam(name = "genre", value = "Movie Genre")
			@RequestParam(value = "genre", required = false) Genre genre,
			@ApiIgnore Pageable pageable
	);
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation("Get movie by id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Movie successfully fetched"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 500, message = "Internal server error"),
	})
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<MovieResponse> getMovieById(@PathVariable("id") Long id);
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation("Save movie")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Movie successfully created"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 500, message = "Internal server error"),
	})
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization jwt token", dataType = "string",
	    		paramType = "header")
    })
	@ResponseStatus(HttpStatus.CREATED)
	ResponseEntity<MovieResponse> save(@ApiParam(value = "Save movie request") @Valid @RequestBody MovieRequest movie);
	
	@PatchMapping(value="/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation("Update movie")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Movie successfully updated"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 500, message = "Internal server error"),
	})
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization jwt token", dataType = "string",
	    		paramType = "header")
    })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	ResponseEntity<Void> update(
			@PathParam(value = "id") Long id,
			@ApiParam(value = "Update movie request") @Valid @RequestBody UpdateMovieRequest movie);
	
	@DeleteMapping(value="/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation("Delete movie")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Movie successfully deleted"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 500, message = "Internal server error"),
	})
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization jwt token", dataType = "string",
	    		paramType = "header")
    })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	ResponseEntity<Void> delete(@PathVariable(value = "id") Long id);
}
