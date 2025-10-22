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

import rs.ac.bg.etf.dipl.domain.Actor;
import rs.ac.bg.etf.dipl.filter.ActorSearchFilter;
import rs.ac.bg.etf.dipl.repository.ActorRepoCustom;

@Repository
public class ActorRepoCustomImpl implements ActorRepoCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<Actor> findAllByFilter(ActorSearchFilter filter) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Actor> query = criteriaBuilder.createQuery(Actor.class);
		Root<Actor> actorRoot = query.from(Actor.class);
		// filter by status
		List<Predicate> predicates = new ArrayList<>();

		if (filter.getName() != null) {
			predicates.add(criteriaBuilder.like(actorRoot.get("name"), "%" + filter.getName() + "%"));
		}
		
		query.where(predicates.toArray(new Predicate[0]));
		// pagination
		TypedQuery<Actor> results = entityManager.createQuery(query);

		if (!filter.getPageable().isUnpaged()) {
			results.setFirstResult(filter.getPageable().getPageNumber() * filter.getPageable().getPageSize());
			results.setMaxResults(filter.getPageable().getPageSize());
		}
		// executing query
		List<Actor> resultList = results.getResultList();
		// get total number of records
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(Actor.class)));
		countQuery.where(predicates.toArray(new Predicate[0]));
		Long totalNumberOfRecords = entityManager.createQuery(countQuery).getSingleResult();
		// packing results into page
		return PageableExecutionUtils.getPage(resultList, filter.getPageable(), () -> totalNumberOfRecords);
	}

}
