package com.itacademy.jd2.raa.telecom.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.raa.telecom.dao.api.IAttributeValueDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;
import com.itacademy.jd2.raa.telecom.dao.api.filter.AttributeValueFilter;
import com.itacademy.jd2.raa.telecom.jdbc.impl.entity.Attribute;
import com.itacademy.jd2.raa.telecom.jdbc.impl.entity.AttributeValue;
import com.itacademy.jd2.raa.telecom.jdbc.impl.util.SQLExecutionException;

@Repository
public class AttributeValueDaoImpl extends AbstractDaoImpl<IAttributeValue, Integer> implements IAttributeValueDao {

	@Override
	public IAttributeValue createEntity() {
		return new AttributeValue();
	}

	@Override
	public void insert(final IAttributeValue entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("insert into %s (attribute_id, value, created, updated) values(?,?,?,?)",
								getTableName()),
						Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getAttribute().getId());
				pStmt.setInt(2, entity.getValue());
				pStmt.setObject(3, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");
				rs.close();
				entity.setId(id);
				// the same should be done in 'update' DAO method
				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	public void update(final IAttributeValue entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("update %s set attribute_id=?, value=?, updated=? where id=?", getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getAttribute().getId());
				pStmt.setInt(2, entity.getValue());
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getId());
				pStmt.executeUpdate();
				// the same should be done in 'update' DAO method
				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	protected IAttributeValue parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {

		final IAttributeValue entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));

		final IAttribute attribute = new Attribute();
		attribute.setId((Integer) resultSet.getObject("attribute_id"));
		entity.setAttribute(attribute);

		entity.setValue(resultSet.getInt("value"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		return entity;
	}

	@Override
	public List<IAttributeValue> find(final AttributeValueFilter filter) {
		return null;
	}

	@Override
	public long getCount(final AttributeValueFilter filter) {
		return 0;
	}

	@Override
	protected String getTableName() {
		return "attribute_value";
	}

	@Override
	public IAttributeValue getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getSum(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
