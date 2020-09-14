package com.itacademy.jd2.raa.telecom.dao.orm.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.itacademy.jd2.raa.telecom.dao.api.IUserAccountDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.UserAccount;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.UserAccount_;

@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {
	protected UserAccountDaoImpl() {
		super(UserAccount.class);
	}

	@Override
	public IUserAccount createEntity() {
		final UserAccount userAccount = new UserAccount();
		return userAccount;
	}

	@Override
	public long getCount(final UserAccountFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		final Root<UserAccount> from = cq.from(UserAccount.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public IUserAccount getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);

		cq.select(from);

		from.fetch(UserAccount_.passportDetails, JoinType.LEFT);
		cq.distinct(true);

		cq.where(cb.equal(from.get(UserAccount_.id), id));

		final TypedQuery<IUserAccount> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);
		cq.select(from);

		from.fetch(UserAccount_.passportDetails, JoinType.LEFT);

		applyFilter(filter, cb, cq, from);

		if (filter.getSortColumn() != null) {
			final Path<?> expression = getSortPath(from, filter.getSortColumn());
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IUserAccount> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(Root<UserAccount> from, String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(UserAccount_.created);
		case "updated":
			return from.get(UserAccount_.updated);
		case "id":
			return from.get(UserAccount_.id);
		case "email":
			return from.get(UserAccount_.email);
		case "user_password":
			return from.get(UserAccount_.userPassword);
		case "role":
			return from.get(UserAccount_.role);
		case "first_name":
			return from.get(UserAccount_.firstName);
		case "last_name":
			return from.get(UserAccount_.lastName);
		case "fathers_name":
			return from.get(UserAccount_.fathersName);
		case "adress":
			return from.get(UserAccount_.adress);
		case "telephone":
			return from.get(UserAccount_.telephone);

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public IUserAccount getUserAndEmail(String userEmail) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);
		cq.select(from);
		cq.where(cb.equal(from.get(UserAccount_.email), userEmail));

		final TypedQuery<IUserAccount> q = em.createQuery(cq);
		return getSingleResult(q);

	}

	@Override
	public IUserAccount getSearchUserAccount() {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);
		cq.select(from);
		cq.orderBy(new OrderImpl(from.get(UserAccount_.created), false));
		final TypedQuery<IUserAccount> q = em.createQuery(cq);
		q.setMaxResults(1);
		List<IUserAccount> resultList = q.getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	private void applyFilter(final UserAccountFilter filter, final CriteriaBuilder cb, final CriteriaQuery<?> cq,
			final Root<UserAccount> from) {
		final List<Predicate> ands = new ArrayList<>();

		final String email = filter.getEmail();
		if (!StringUtils.isEmpty(email)) {
			ands.add(cb.equal(from.get(UserAccount_.email), email));
		}

		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}
	}


}
