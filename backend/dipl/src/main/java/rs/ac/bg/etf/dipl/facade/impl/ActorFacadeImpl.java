package rs.ac.bg.etf.dipl.facade.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import rs.ac.bg.etf.dipl.dao.ActorDAO;
import rs.ac.bg.etf.dipl.dao.ImageDAO;
import rs.ac.bg.etf.dipl.dto.actor.ActorDto;
import rs.ac.bg.etf.dipl.dto.actor.CreateActorDto;
import rs.ac.bg.etf.dipl.dto.image.ImageDto;
import rs.ac.bg.etf.dipl.dto.movie.MovieDto;
import rs.ac.bg.etf.dipl.facade.ActorFacade;
import rs.ac.bg.etf.dipl.filter.ActorSearchFilter;
import rs.ac.bg.etf.dipl.mapper.api.ActorApiMapper;
import rs.ac.bg.etf.dipl.web.model.actor.ActorRequest;
import rs.ac.bg.etf.dipl.web.model.actor.ActorResponse;

@Service
public class ActorFacadeImpl implements ActorFacade {

	@Autowired
	private ActorDAO actorDAO;
	@Autowired
	private ActorApiMapper mapper;

	@Override
	public Page<ActorResponse> getActorsByParams(ActorSearchFilter filter) {
		Page<ActorDto> actorsDto = actorDAO.getActors(filter);

		return PageableExecutionUtils.getPage(actorsDto.stream().map(mapper::dtoToApi).collect(Collectors.toList()),
			filter.getPageable(),
			actorsDto::getTotalElements);
	}

	@Override
	public ActorResponse getActorById(Long id) {
		ActorDto actorDto = actorDAO.getActorById(id);
		return mapper.dtoToApi(actorDto);
	}

	@Override
	public ActorResponse addActor(ActorRequest actor) {
		CreateActorDto createActorDto = mapper.apiToDto(actor);
		return mapper.dtoToApi(actorDAO.addActor(createActorDto));
	}
}
