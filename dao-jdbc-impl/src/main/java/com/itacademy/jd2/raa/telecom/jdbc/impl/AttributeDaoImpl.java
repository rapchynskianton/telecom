package com.itacademy.jd2.raa.telecom.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.raa.telecom.dao.api.IAttributeDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;
import com.itacademy.jd2.raa.telecom.dao.api.filter.AttributeFilter;
import com.itacademy.jd2.raa.telecom.jdbc.impl.entity.Attribute;
import com.itacademy.jd2.raa.telecom.jdbc.impl.util.PreparedStatementAction;

@Repository
public class AttributeDaoImpl extends AbstractDaoImpl<IAttribute, Integer> implements IAttributeDao {

	@Override
	public IAttribute createEntity() {
		return new Attribute();
	}

	@Override
	public void insert(IAttribute entity) {
		executeStatement(new PreparedStatementAction<IAttribute>(
				String.format("insert into %s (name, created, updated) values(?,?,?)", getTableName()), true) {
			@Override
			public IAttribute doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();

				entity.setId(id);
				return entity;
			}
		});
	}

	@Override
	public void update(IAttribute entity) {
		executeStatement(new PreparedStatementAction<IAttribute>(
				String.format("update %s set name=?, updated=? where id=?", getTableName())) {
			@Override
			public IAttribute doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getId());
				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected IAttribute parseRow(final ResultSet resultSet) throws SQLException {
		final IAttribute entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "attribute";
	}

	@Override
	public List<IAttribute> find(AttributeFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(AttributeFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public Integer getSum(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
