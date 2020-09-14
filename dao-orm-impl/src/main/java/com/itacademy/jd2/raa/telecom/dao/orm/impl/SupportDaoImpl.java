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

import com.itacademy.jd2.raa.telecom.dao.api.ISupportDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ISupport;
import com.itacademy.jd2.raa.telecom.dao.api.filter.SupportFilter;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.Support;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.Support_;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.UserAccount_;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.UserCabinet_;

@Repository
public class SupportDaoImpl extends AbstractDaoImpl<ISupport, Integer> implements ISupportDao {
	protected SupportDaoImpl() {
		super(Support.class);
	}

	@Override
	public ISupport createEntity() {
		final Support support = new Support();
		return support;
	}

	@Override
	public long getCount(final SupportFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		final Root<Support> from = cq.from(Support.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public ISupport getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ISupport> cq = cb.createQuery(ISupport.class);
		final Root<Support> from = cq.from(Support.class);

		cq.select(from);

		from.fetch(Support_.userCabinet, JoinType.LEFT);
		from.fetch(Support_.userAccount, JoinType.LEFT);

		cq.where(cb.equal(from.get(Support_.id), id));

		final TypedQuery<ISupport> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<ISupport> find(SupportFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ISupport> cq = cb.createQuery(ISupport.class);
		final Root<Support> from = cq.from(Support.class);
		cq.select(from);

		from.fetch(Support_.userCabinet, JoinType.LEFT);
		from.fetch(Support_.userAccount, JoinType.LEFT);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}
		
		if (filter.getId() != null) {
			cq.where(cb.equal(from.get(Support_.userCabinet), filter.getId()));
		}
		final TypedQuery<ISupport> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<ISupport> resultList = q.getResultList();
		return resultList;
	}

	private Path<?> getSortPath(final Root<Support> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(Support_.id);
		case "problem_name":
			return from.get(Support_.problemName);
		case "problem":
			return from.get(Support_.problem);
		case "status":
			return from.get(Support_.status);
		case "user_cabinet":
			return from.get(Support_.userCabinet).get(UserCabinet_.id);
		case "user_account":
			return from.get(Support_.userAccount).get(UserAccount_.email);
		case "created":
			return from.get(Support_.created);
		case "updated":
			return from.get(Support_.updated);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
