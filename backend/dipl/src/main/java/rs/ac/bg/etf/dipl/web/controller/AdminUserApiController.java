package rs.ac.bg.etf.dipl.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.bg.etf.dipl.facade.UserFacade;
import rs.ac.bg.etf.dipl.mapper.FilterMapper;
import rs.ac.bg.etf.dipl.web.api.AdminUserApi;
import rs.ac.bg.etf.dipl.web.model.auth.BanRequest;
import rs.ac.bg.etf.dipl.web.model.user.UserResponse;

@RestController
public class AdminUserApiController implements AdminUserApi {

	@Autowired
	private UserFacade userFacade;
	
	@Override
	public ResponseEntity<Page<UserResponse>> getUsersByParams(String username, Pageable pageable) {
		return ResponseEntity.ok(userFacade.getUsers(FilterMapper.toSearchUserFilter(username, pageable)));
	}
	
	@Override
	public ResponseEntity<UserResponse> getUserByUsername(String username) {
		UserResponse ur = userFacade.getUser(username);
		return ResponseEntity.ok(ur);
	}

	@Override
	public ResponseEntity<Void> banUser(BanRequest bannedUser) {
		userFacade.banUser(bannedUser.getUsername(), bannedUser.isBanOrUnban());
		return ResponseEntity.noContent().build();
	}
}
