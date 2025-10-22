package rs.ac.bg.etf.dipl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.bg.etf.dipl.domain.Image;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

}
