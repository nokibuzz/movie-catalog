package rs.ac.bg.etf.dipl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.bg.etf.dipl.domain.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long>, MovieRepoCustom {

}
