package rs.ac.bg.etf.dipl.repository;

import org.springframework.data.domain.Page;

import rs.ac.bg.etf.dipl.domain.Actor;
import rs.ac.bg.etf.dipl.filter.ActorSearchFilter;

public interface ActorRepoCustom {

	Page<Actor> findAllByFilter(ActorSearchFilter filter);
}
