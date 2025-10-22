package rs.ac.bg.etf.dipl.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import rs.ac.bg.etf.dipl.domain.Movie;
import rs.ac.bg.etf.dipl.filter.MovieSearchFilter;
import rs.ac.bg.etf.dipl.repository.MovieRepoCustom;

@Repository
public class MovieRepoCustomImpl implements MovieRepoCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Movie> findAllByFilter(MovieSearchFilter filter) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Movie> query = criteriaBuilder.createQuery(Movie.class);
		Root<Movie> movieRoot = query.from(Movie.class);
		// filter by status
		List<Predicate> predicates = new ArrayList<>();

		if (filter.getTitle() != null) {
			predicates.add(criteriaBuilder.like(movieRoot.get("title"), "%" + filter.getTitle() + "%"));
		}
		if (filter.getGenre() != null) {
			predicates.add(criteriaBuilder.like(movieRoot.get("genres"), "%" + filter.getGenre()+ "%"));
		}
		
		query.where(predicates.toArray(new Predicate[0]));
		// pagination
		TypedQuery<Movie> results = entityManager.createQuery(query);

		if (!filter.getPageable().isUnpaged()) {
			results.setFirstResult(filter.getPageable().getPageNumber() * filter.getPageable().getPageSize());
			results.setMaxResults(filter.getPageable().getPageSize());
		}
		// executing query
		List<Movie> resultList = results.getResultList();
		// get total number of records
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(Movie.class)));
		countQuery.where(predicates.toArray(new Predicate[0]));
		Long totalNumberOfRecords = entityManager.createQuery(countQuery).getSingleResult();
		// packing results into page
		return PageableExecutionUtils.getPage(resultList, filter.getPageable(), () -> totalNumberOfRecords);
	}
}
