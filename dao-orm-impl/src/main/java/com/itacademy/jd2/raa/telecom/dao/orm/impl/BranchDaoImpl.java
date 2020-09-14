package com.itacademy.jd2.raa.telecom.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.raa.telecom.dao.api.IBranchDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IBranch;
import com.itacademy.jd2.raa.telecom.dao.api.filter.BranchFilter;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.Branch;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.Branch_;

@Repository
public class BranchDaoImpl extends AbstractDaoImpl<IBranch, Integer> implements IBranchDao {
	protected BranchDaoImpl() {
		super(Branch.class);
	}

	@Override
	public IBranch createEntity() {
		final Branch branch = new Branch();
		return branch;
	}

	@Override
	public long getCount(final BranchFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<Branch> from = cq.from(Branch.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public List<IBranch> find(BranchFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IBranch> cq = cb.createQuery(IBranch.class);

		final Root<Branch> from = cq.from(Branch.class);
		cq.select(from);

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Branch, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IBranch> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Branch, ?> toMetamodelFormat(final String sortColumn) {
		switch (sortColumn) {
		case "created":
			return Branch_.created;
		case "updated":
			return Branch_.updated;
		case "id":
			return Branch_.id;
		case "region":
			return Branch_.region;
		case "adress":
			return Branch_.adress;
		case "telephone":
			return Branch_.telephone;

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
