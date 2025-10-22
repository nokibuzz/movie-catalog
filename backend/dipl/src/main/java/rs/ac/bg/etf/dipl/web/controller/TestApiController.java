package rs.ac.bg.etf.dipl.web.controller;

import org.springframework.web.bind.annotation.RestController;

import rs.ac.bg.etf.dipl.web.api.TestApi;

@RestController
public class TestApiController implements TestApi {

	@Override
	public String allAccess() {
		return "Public Content.";
	}
	
	@Override
	public String userAccess() {
		return "User Content.";
	}

	@Override
	public String adminAccess() {
		return "Admin Board.";
	}
}
