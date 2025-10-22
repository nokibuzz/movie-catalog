package rs.ac.bg.etf.dipl.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rs.ac.bg.etf.dipl.dao.ActorDAO;
import rs.ac.bg.etf.dipl.domain.Actor;
import rs.ac.bg.etf.dipl.dto.actor.ActorDto;
import rs.ac.bg.etf.dipl.dto.actor.CreateActorDto;
import rs.ac.bg.etf.dipl.filter.ActorSearchFilter;
import rs.ac.bg.etf.dipl.mapper.domain.ActorDomainMapper;
import rs.ac.bg.etf.dipl.repository.ActorRepo;
import rs.ac.bg.etf.dipl.util.ExceptionFactoryUtil;

@Slf4j
@Transactional
@Service
public class ActorDAOImpl implements ActorDAO {

	@Autowired
	private ActorRepo actorRepo;
	@Autowired
	private ActorDomainMapper mapper;
	
	@Override
	public Page<ActorDto> getActors(ActorSearchFilter filter) {
		log.info("Fetching actors by filter {}", filter);
		Page<Actor> actorsPageable = actorRepo.findAllByFilter(filter);
		List<ActorDto> actorsDto = actorsPageable.getContent()
				.stream()
				.map(mapper::domainToDto)
				.collect(Collectors.toList());
		
		return PageableExecutionUtils.getPage(actorsDto, filter.getPageable(), actorsPageable::getTotalElements);
	}
	
	@Override
	public ActorDto getActorById(Long id) {
		log.info("Fetching movie with id {}", id);
		Actor actor = actorRepo.findById(id)
				.orElseThrow(() -> ExceptionFactoryUtil.actorNotFound(id));
		return mapper.domainToDto(actor);
	}

	@Override
	public ActorDto addActor(CreateActorDto actor) {
		log.info("Saving actor {}", actor);
		Actor domain = actorRepo.save(mapper.dtoToDomain(actor));
		ActorDto actorToReturn = mapper.domainToDto(domain);
		return actorToReturn;
	}

	@Override
	public List<ActorDto> findActorsById(List<Long> actors) {
		
		log.info("Finding actors with ids: {}", actors);
		List<Actor> returnedActors = actorRepo.findByIdIn(actors);
		
		return returnedActors.stream()
				.map(mapper::domainToDto)
				.collect(Collectors.toList());
	}
}
