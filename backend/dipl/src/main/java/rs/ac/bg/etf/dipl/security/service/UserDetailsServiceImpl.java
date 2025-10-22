package rs.ac.bg.etf.dipl.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rs.ac.bg.etf.dipl.domain.User;
import rs.ac.bg.etf.dipl.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepo userRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " is not found!"));

		return UserDetailsImpl.build(user);
	}
	
	// This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepo.findById(id)
        		.orElseThrow(() -> new UsernameNotFoundException("User with id : " + id + " is not found!"));

        return UserDetailsImpl.build(user);
    }
}
