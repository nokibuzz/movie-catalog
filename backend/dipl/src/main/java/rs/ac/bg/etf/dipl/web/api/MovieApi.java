package rs.ac.bg.etf.dipl.web.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import rs.ac.bg.etf.dipl.web.model.movie.MovieResponse;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "Movie API", description = "WEB API for managing movies")
@RequestMapping("v1/movie")
public interface MovieApi {

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation("Get movies")
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
}
