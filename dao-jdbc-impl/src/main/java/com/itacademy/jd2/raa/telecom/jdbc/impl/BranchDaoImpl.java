package com.itacademy.jd2.raa.telecom.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.raa.telecom.dao.api.IBranchDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IBranch;
import com.itacademy.jd2.raa.telecom.dao.api.filter.BranchFilter;
import com.itacademy.jd2.raa.telecom.jdbc.impl.entity.Branch;
import com.itacademy.jd2.raa.telecom.jdbc.impl.util.PreparedStatementAction;
import com.itacademy.jd2.raa.telecom.jdbc.impl.util.SQLExecutionException;

@Repository
public class BranchDaoImpl extends AbstractDaoImpl<IBranch, Integer> implements IBranchDao {

	@Override
	public IBranch createEntity() {
		return new Branch();
	}
	
	@Override
	public void deleteAll() {
		try (Connection c = getConnection();
				PreparedStatement deleteAllStmt = c.prepareStatement("delete from " + getTableName());) {
			c.setAutoCommit(false);
			try {
				deleteAllStmt.executeUpdate();
				deleteAllStmt.close();
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
	public void insert(IBranch entity) {
		executeStatement(new PreparedStatementAction<IBranch>(
				String.format("insert into %s (region, adress, telephone, created, updated) values(?,?,?,?,?)",
						getTableName()),
				true) {
			@Override
			public IBranch doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getRegion());
				pStmt.setString(2, entity.getAdress());
				pStmt.setString(3, entity.getTelephone());
				pStmt.setObject(4, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);
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
	public void update(IBranch entity) {
		executeStatement(new PreparedStatementAction<IBranch>(
				String.format("update %s set region=?, adress=?, telephone=?, updated=? where id=?", getTableName())) {
			@Override
			public IBranch doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getRegion());
				pStmt.setString(2, entity.getAdress());
				pStmt.setString(3, entity.getTelephone());
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(5, entity.getId());
				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected IBranch parseRow(final ResultSet resultSet) throws SQLException {
		final IBranch entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setRegion(resultSet.getString("region"));
		entity.setAdress(resultSet.getString("adress"));
		entity.setTelephone(resultSet.getString("telephone"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "branch";
	}

	@Override
	public List<IBranch> find(BranchFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(BranchFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public Integer getSum(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
