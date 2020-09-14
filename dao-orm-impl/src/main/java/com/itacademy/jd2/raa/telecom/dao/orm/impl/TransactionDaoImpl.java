package com.itacademy.jd2.raa.telecom.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.raa.telecom.dao.api.ITransactionDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.raa.telecom.dao.api.filter.TransactionFilter;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.AttributeValue_;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.Transaction;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.Transaction_;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.UserCabinet_;

@Repository
public class TransactionDaoImpl extends AbstractDaoImpl<ITransaction, Integer> implements ITransactionDao {
	protected TransactionDaoImpl() {
		super(Transaction.class);
	}

	@Override
	public ITransaction createEntity() {
		final Transaction transaction = new Transaction();
		return transaction;
	}

	@Override
	public long getCount(final TransactionFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		final Root<Transaction> from = cq.from(Transaction.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public ITransaction getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ITransaction> cq = cb.createQuery(ITransaction.class);
		final Root<Transaction> from = cq.from(Transaction.class);

		cq.select(from);

		from.fetch(Transaction_.userCabinet, JoinType.LEFT);

		cq.where(cb.equal(from.get(Transaction_.id), id));

		final TypedQuery<ITransaction> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<ITransaction> find(TransactionFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ITransaction> cq = cb.createQuery(ITransaction.class);
		final Root<Transaction> from = cq.from(Transaction.class);
		cq.select(from);

		from.fetch(Transaction_.userCabinet, JoinType.LEFT);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		if (filter.getId()!=null) {
		cq.where(cb.equal(from.get(Transaction_.id), filter.getId()));
		}
		
		final TypedQuery<ITransaction> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<ITransaction> resultList = q.getResultList();
		return resultList;
	}


	private Path<?> getSortPath(final Root<Transaction> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(Transaction_.id);
		case "value":
			return from.get(Transaction_.value);
		case "user_cabinet":
			return from.get(Transaction_.userCabinet).get(UserCabinet_.id);
		case "description":
			return from.get(Transaction_.description);
		case "created":
			return from.get(Transaction_.created);
		case "updated":
			return from.get(Transaction_.updated);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
