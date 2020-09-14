package com.itacademy.jd2.raa.telecom.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.raa.telecom.dao.api.ITariffPlanDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.filter.TariffPlanFilter;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.TariffPlan;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.TariffPlan_;

@Repository
public class TariffPlanDaoImpl extends AbstractDaoImpl<ITariffPlan, Integer> implements ITariffPlanDao {

	protected TariffPlanDaoImpl() {
		super(TariffPlan.class);
	}

	@Override
	public ITariffPlan createEntity() {
		final TariffPlan tariffPlan = new TariffPlan();
		return tariffPlan;
	}

	@Override
	public long getCount(final TariffPlanFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		final Root<TariffPlan> from = cq.from(TariffPlan.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public ITariffPlan getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ITariffPlan> cq = cb.createQuery(ITariffPlan.class);
		final Root<TariffPlan> from = cq.from(TariffPlan.class);
		cq.select(from);

		from.fetch(TariffPlan_.attributeValue, JoinType.LEFT);
		cq.distinct(true);
		cq.where(cb.equal(from.get(TariffPlan_.id), id));

		final TypedQuery<ITariffPlan> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<ITariffPlan> find(final TariffPlanFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ITariffPlan> cq = cb.createQuery(ITariffPlan.class);

		final Root<TariffPlan> from = cq.from(TariffPlan.class); // select from model

		cq.select(from);
		from.fetch(TariffPlan_.attributeValue, JoinType.LEFT);
		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		if (filter.getAttributeValue() != null) {
			cq.where(cb.equal(from.get(TariffPlan_.attributeValue), filter.getAttributeValue()));
		}

		final TypedQuery<ITariffPlan> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<ITariffPlan> resultList = q.getResultList();
		return resultList;
	}

	private Path<?> getSortPath(final Root<TariffPlan> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(TariffPlan_.id);
		case "name":
			return from.get(TariffPlan_.name);
		case "costPerUnit":
			return from.get(TariffPlan_.costPerUnit);
		case "unit":
			return from.get(TariffPlan_.unit);
		case "type":
			return from.get(TariffPlan_.type);
		case "created":
			return from.get(TariffPlan_.created);
		case "updated":
			return from.get(TariffPlan_.updated);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
