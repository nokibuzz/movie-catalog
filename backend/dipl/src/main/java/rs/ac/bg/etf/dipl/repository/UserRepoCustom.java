package rs.ac.bg.etf.dipl.repository;

import org.springframework.data.domain.Page;

import rs.ac.bg.etf.dipl.domain.User;
import rs.ac.bg.etf.dipl.filter.UserSearchFilter;

public interface UserRepoCustom {

	Page<User> findAllByFilter(UserSearchFilter filter);
}
