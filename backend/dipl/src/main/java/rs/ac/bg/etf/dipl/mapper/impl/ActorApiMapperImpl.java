package rs.ac.bg.etf.dipl.mapper.impl;

import org.springframework.stereotype.Component;

import rs.ac.bg.etf.dipl.dto.actor.ActorDto;
import rs.ac.bg.etf.dipl.dto.actor.CreateActorDto;
import rs.ac.bg.etf.dipl.mapper.api.ActorApiMapper;
import rs.ac.bg.etf.dipl.web.model.actor.ActorRequest;
import rs.ac.bg.etf.dipl.web.model.actor.ActorResponse;

@Component
public class ActorApiMapperImpl implements ActorApiMapper {
	
	@Override
	public CreateActorDto apiToDto(ActorRequest actor) {
		return CreateActorDto.builder()
				.name(actor.getName())
				.birthday(actor.getBirthday())
				.roles(actor.getRoles())
				.build();
	}

	@Override
	public ActorResponse dtoToApi(ActorDto actor) {
		return ActorResponse.builder()
				.id(actor.getId())
				.name(actor.getName())
				.birthday(actor.getBirthday())
				.roles(actor.getRoles())
				.build();
	}
}
