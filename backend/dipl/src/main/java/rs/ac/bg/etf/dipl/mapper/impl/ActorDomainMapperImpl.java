package rs.ac.bg.etf.dipl.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rs.ac.bg.etf.dipl.domain.Actor;
import rs.ac.bg.etf.dipl.dto.actor.ActorDto;
import rs.ac.bg.etf.dipl.dto.actor.CreateActorDto;
import rs.ac.bg.etf.dipl.mapper.domain.ActorDomainMapper;
import rs.ac.bg.etf.dipl.mapper.domain.ImageDomainMapper;

@Component
public class ActorDomainMapperImpl implements ActorDomainMapper {

	@Autowired
	private ImageDomainMapper imageMapper;
	
	@Override
	public Actor dtoToDomain(CreateActorDto actor) {
		return Actor.builder()
				.name(actor.getName())
				.birthday(actor.getBirthday())
				.roles(actor.getRoles())
				.build();
	}
	
	@Override
	public Actor dtoToDomain(ActorDto actor) {
		return Actor.builder()
				.id(actor.getId())
				.name(actor.getName())
				.birthday(actor.getBirthday())
				.roles(actor.getRoles())
				.build();
	}
	
	@Override
	public ActorDto domainToDto(Actor actor) {
		return ActorDto.builder()
				.id(actor.getId())
				.name(actor.getName())
				.birthday(actor.getBirthday())
				.roles(actor.getRoles())
				.build();
	}
}
