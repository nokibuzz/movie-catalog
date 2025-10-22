package rs.ac.bg.etf.dipl.domain;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.etf.dipl.domain.base.BaseEntityAudit;
import rs.ac.bg.etf.dipl.enumeration.CrewRole;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crew extends BaseEntityAudit {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private Instant birthday;
	
//	@ManyToMany(mappedBy = "crews")
//	private List<Movie> movies;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "crew_role")
	private CrewRole crewRole;
	
	@Lob
	private Byte[] picture;
	
	@Column(name = "active_from")
	private String activeFrom;
}
