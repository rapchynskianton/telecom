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

import com.itacademy.jd2.raa.telecom.dao.api.IConnectedTariffPlanDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IConnectedTariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.filter.ConnectedTariffPlanFilter;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.ConnectedTariffPlan;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.ConnectedTariffPlan_;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.TariffPlan_;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.UserCabinet_;

@Repository
public class ConnectedTariffPlanDaoImpl extends AbstractDaoImpl<IConnectedTariffPlan, Integer>
		implements IConnectedTariffPlanDao {

	protected ConnectedTariffPlanDaoImpl() {
		super(ConnectedTariffPlan.class);
	}

	@Override
	public IConnectedTariffPlan createEntity() {
		final ConnectedTariffPlan connectedTariffPlan = new ConnectedTariffPlan();
		return connectedTariffPlan;
	}

	@Override
	public long getCount(final ConnectedTariffPlanFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		final Root<ConnectedTariffPlan> from = cq.from(ConnectedTariffPlan.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<IConnectedTariffPlan> find(ConnectedTariffPlanFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IConnectedTariffPlan> cq = cb.createQuery(IConnectedTariffPlan.class);
		final Root<ConnectedTariffPlan> from = cq.from(ConnectedTariffPlan.class);
		cq.select(from);
		from.fetch(ConnectedTariffPlan_.userCabinet, JoinType.LEFT);
		from.fetch(ConnectedTariffPlan_.tariffPlan, JoinType.LEFT);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IConnectedTariffPlan> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IConnectedTariffPlan> resultList = q.getResultList();
		return resultList;
	}

	private Path<?> getSortPath(final Root<ConnectedTariffPlan> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(ConnectedTariffPlan_.id);
		case "user_cabinet":
			return from.get(ConnectedTariffPlan_.userCabinet).get(UserCabinet_.id);
		case "tariff_plan":
			return from.get(ConnectedTariffPlan_.tariffPlan).get(TariffPlan_.name);
		case "activation_date":
			return from.get(ConnectedTariffPlan_.activationDate);
		case "sum_cost":
			return from.get(ConnectedTariffPlan_.sumCost);
		case "created":
			return from.get(ConnectedTariffPlan_.created);
		case "updated":
			return from.get(ConnectedTariffPlan_.updated);

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public IConnectedTariffPlan getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IConnectedTariffPlan> cq = cb.createQuery(IConnectedTariffPlan.class);
		final Root<ConnectedTariffPlan> from = cq.from(ConnectedTariffPlan.class);

		cq.select(from);

		from.fetch(ConnectedTariffPlan_.userCabinet, JoinType.LEFT);
		from.fetch(ConnectedTariffPlan_.tariffPlan, JoinType.LEFT);

		cq.where(cb.equal(from.get(ConnectedTariffPlan_.id), id));

		final TypedQuery<IConnectedTariffPlan> q = em.createQuery(cq);

		return getSingleResult(q);
	}
}
