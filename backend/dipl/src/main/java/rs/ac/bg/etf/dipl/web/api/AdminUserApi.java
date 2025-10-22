package rs.ac.bg.etf.dipl.web.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import rs.ac.bg.etf.dipl.web.model.auth.BanRequest;
import rs.ac.bg.etf.dipl.web.model.user.UserResponse;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "Admin User API", description = "WEB API for managing users as admin")
@RequestMapping("v1/admin/user")
public interface AdminUserApi {

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation("Get list of users in the system")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Users successfully fetched"),
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
	ResponseEntity<Page<UserResponse>> getUsersByParams(
			@ApiParam(name = "username", value = "User's username")
			@RequestParam(value = "username", required = false) String username,
			@ApiIgnore Pageable pageable
	);
	
	@GetMapping(value = "/{username}" ,produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation("Get user by username in the system")
	@ApiResponses({
		@ApiResponse(code = 200, message = "User successfully fetched"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 500, message = "Internal server error"),
	})
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<UserResponse> getUserByUsername(@PathVariable("username") String username);
	
	@PatchMapping(value="/ban", consumes = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation("Ban or unban user")
	@ApiResponses({
		@ApiResponse(code = 204, message = "User successfully banned or unbanned"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 500, message = "Internal server error"),
	})
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization jwt token", dataType = "string",
	    		paramType = "header")
    })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	ResponseEntity<Void> banUser(
			@ApiParam(value = "User ban/unban request") @RequestBody BanRequest bannedUser);
}
