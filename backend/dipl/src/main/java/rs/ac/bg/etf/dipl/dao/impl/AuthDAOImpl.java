package rs.ac.bg.etf.dipl.dao.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.bg.etf.dipl.dao.AuthDAO;
import rs.ac.bg.etf.dipl.domain.Role;
import rs.ac.bg.etf.dipl.domain.User;
import rs.ac.bg.etf.dipl.dto.auth.CreateUserDto;
import rs.ac.bg.etf.dipl.dto.auth.UserDto;
import rs.ac.bg.etf.dipl.enumeration.RoleConst;
import rs.ac.bg.etf.dipl.mapper.domain.AuthDomainMapper;
import rs.ac.bg.etf.dipl.repository.RoleRepo;
import rs.ac.bg.etf.dipl.repository.UserRepo;
import rs.ac.bg.etf.dipl.util.ExceptionFactoryUtil;

@Service
public class AuthDAOImpl implements AuthDAO {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private AuthDomainMapper mapper;
	
	@Override
	public Boolean existsByUsername(String username) {
		return userRepo.existsByUsername(username);
	}
	@Override
	public Boolean existsByEmail(String email) {
		return userRepo.existsByEmail(email);
	}
	@Override
	public Role findByName(RoleConst role) {
		return roleRepo.findByName(role)
				.orElseThrow(() -> ExceptionFactoryUtil.roleNotFound(role.name()));
	}
	
	@Override
	public UserDto saveUserRoles(CreateUserDto user, Set<Role> roles) {
		User newUser = mapper.dtoToDomain(user);
		newUser.setRoles(roles);
		User savedUser = userRepo.save(newUser);
		return mapper.domainToDto(savedUser);
	}
}
