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

import com.itacademy.jd2.raa.telecom.dao.api.IUserCabinetDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.dao.api.filter.UserCabinetFilter;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.Branch_;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.UserAccount_;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.UserCabinet;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.UserCabinet_;

@Repository
public class UserCabinetDaoImpl extends AbstractDaoImpl<IUserCabinet, Integer> implements IUserCabinetDao {

	protected UserCabinetDaoImpl() {
		super(UserCabinet.class);
	}

	@Override
	public IUserCabinet createEntity() {
		final UserCabinet userCabinet = new UserCabinet();
		return userCabinet;
	}

	@Override
	public long getCount(final UserCabinetFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		final Root<UserCabinet> from = cq.from(UserCabinet.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public IUserCabinet getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IUserCabinet> cq = cb.createQuery(IUserCabinet.class);
		final Root<UserCabinet> from = cq.from(UserCabinet.class);

		cq.select(from);

		from.fetch(UserCabinet_.userAccount, JoinType.LEFT);
		from.fetch(UserCabinet_.branch, JoinType.LEFT);

		cq.where(cb.equal(from.get(UserCabinet_.id), id));

		final TypedQuery<IUserCabinet> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IUserCabinet> find(UserCabinetFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IUserCabinet> cq = cb.createQuery(IUserCabinet.class);
		final Root<UserCabinet> from = cq.from(UserCabinet.class);
		cq.select(from);

		from.fetch(UserCabinet_.userAccount, JoinType.LEFT);
		from.fetch(UserCabinet_.branch, JoinType.LEFT);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		if (filter.getId() != null) {
			cq.where(cb.equal(from.get(UserCabinet_.userAccount), filter.getId()));
		}

		final TypedQuery<IUserCabinet> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IUserCabinet> resultList = q.getResultList();
		return resultList;
	}

	private Path<?> getSortPath(final Root<UserCabinet> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(UserCabinet_.id);
		case "activation_date":
			return from.get(UserCabinet_.activationDate);
		case "status":
			return from.get(UserCabinet_.status);
		case "user_account":
			return from.get(UserCabinet_.userAccount).get(UserAccount_.email);
		case "branch":
			return from.get(UserCabinet_.branch).get(Branch_.region);
		case "created":
			return from.get(UserCabinet_.created);
		case "updated":
			return from.get(UserCabinet_.updated);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
