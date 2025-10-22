package rs.ac.bg.etf.dipl.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.etf.dipl.domain.base.BaseEntityAudit;
import rs.ac.bg.etf.dipl.enumeration.Genre;

@SuppressWarnings("serial")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "sequence_generator", sequenceName = "x_movie_id_seq")
public class Movie extends BaseEntityAudit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String description;
	
	@Column(name = "trailer_url")
	private String trailerUrl;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
	Image image;
	
	private int duration;
	
	private String ranking;
	
	private String year;
	
	@ManyToMany(cascade = {
		    CascadeType.PERSIST,
		    CascadeType.MERGE
		})
	@JoinTable(
		name = "movie_actors",
		joinColumns = @JoinColumn(name = "movie_id"),
		inverseJoinColumns = @JoinColumn(name = "actor_id")
	)
	private List<Actor> actors;

//	@ElementCollection(fetch = FetchType.EAGER)
//	@CollectionTable(name = "Genre", joinColumns = @JoinColumn(name = "id"))
//	@Enumerated(EnumType.STRING)
	private String genres;
}
