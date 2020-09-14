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

import com.itacademy.jd2.raa.telecom.dao.api.IAttributeValueDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;
import com.itacademy.jd2.raa.telecom.dao.api.filter.AttributeValueFilter;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.AttributeValue;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.AttributeValue_;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.Attribute_;

@Repository
public class AttributeValueDaoImpl extends AbstractDaoImpl<IAttributeValue, Integer> implements IAttributeValueDao {
	protected AttributeValueDaoImpl() {
		super(AttributeValue.class);
	}

	@Override
	public IAttributeValue createEntity() {
		final AttributeValue attributeValue = new AttributeValue();
		return attributeValue;
	}

	@Override
	public long getCount(final AttributeValueFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		final Root<AttributeValue> from = cq.from(AttributeValue.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public IAttributeValue getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IAttributeValue> cq = cb.createQuery(IAttributeValue.class);
		final Root<AttributeValue> from = cq.from(AttributeValue.class);

		cq.select(from);

		from.fetch(AttributeValue_.attribute, JoinType.LEFT);

		cq.where(cb.equal(from.get(AttributeValue_.id), id));

		final TypedQuery<IAttributeValue> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IAttributeValue> find(AttributeValueFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IAttributeValue> cq = cb.createQuery(IAttributeValue.class);
		final Root<AttributeValue> from = cq.from(AttributeValue.class);
		cq.select(from);

		from.fetch(AttributeValue_.attribute, JoinType.LEFT);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		if (filter.getId() != null) {
			cq.where(cb.equal(from.get(AttributeValue_.attribute), filter.getId()));
		}
		final TypedQuery<IAttributeValue> q = em.createQuery(cq);

		setPaging(filter, q);
		final List<IAttributeValue> resultList = q.getResultList();

		return resultList;
	}

	private Path<?> getSortPath(final Root<AttributeValue> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(AttributeValue_.id);
		case "name":
			return from.get(AttributeValue_.value);
		case "attribute":
			return from.get(AttributeValue_.attribute).get(Attribute_.name);
		case "created":
			return from.get(AttributeValue_.created);
		case "updated":
			return from.get(AttributeValue_.updated);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
