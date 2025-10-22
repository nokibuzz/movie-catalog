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

import rs.ac.bg.etf.dipl.domain.User;
import rs.ac.bg.etf.dipl.filter.UserSearchFilter;
import rs.ac.bg.etf.dipl.repository.UserRepoCustom;

@Repository
public class UserRepoCustomImpl implements UserRepoCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<User> findAllByFilter(UserSearchFilter filter) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
		Root<User> userRoot = query.from(User.class);
		// filter by status
		List<Predicate> predicates = new ArrayList<>();

		if (filter.getUsername() != null) {
			predicates.add(criteriaBuilder.like(userRoot.get("username"), "%" + filter.getUsername() + "%"));
		}
		
		predicates.add(criteriaBuilder.notEqual(userRoot.get("username"), "admin"));
		
		query.where(predicates.toArray(new Predicate[0]));
		// pagination
		TypedQuery<User> results = entityManager.createQuery(query);

		if (!filter.getPageable().isUnpaged()) {
			results.setFirstResult(filter.getPageable().getPageNumber() * filter.getPageable().getPageSize());
			results.setMaxResults(filter.getPageable().getPageSize());
		}
		// executing query
		List<User> resultList = results.getResultList();
		// get total number of records
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(User.class)));
		countQuery.where(predicates.toArray(new Predicate[0]));
		Long totalNumberOfRecords = entityManager.createQuery(countQuery).getSingleResult();
		// packing results into page
		return PageableExecutionUtils.getPage(resultList, filter.getPageable(), () -> totalNumberOfRecords);
	}
}
