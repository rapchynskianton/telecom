package com.itacademy.jd2.raa.telecom.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.raa.telecom.dao.api.ISupportDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ISupport;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.dao.api.filter.SupportFilter;
import com.itacademy.jd2.raa.telecom.jdbc.impl.entity.Support;
import com.itacademy.jd2.raa.telecom.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.raa.telecom.jdbc.impl.entity.UserCabinet;
import com.itacademy.jd2.raa.telecom.jdbc.impl.util.PreparedStatementAction;
import com.itacademy.jd2.raa.telecom.jdbc.impl.util.SQLExecutionException;

@Repository
public class SupportDaoImpl extends AbstractDaoImpl<ISupport, Integer> implements ISupportDao {

	@Override
	public ISupport createEntity() {
		return new Support();
	}

	@Override
	public void insert(final ISupport entity) {
		executeStatement(new PreparedStatementAction<ISupport>(String.format(
				"insert into %s (problem_name, problem, status, user_cabinet_id, manager_id, created, updated) values(?,?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public ISupport doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getProblemName());
				pStmt.setString(2, entity.getProblem());
				pStmt.setBoolean(3, entity.getStatus());
				pStmt.setInt(4, entity.getUserCabinet().getId());
				pStmt.setInt(5, entity.getUserAccount().getId());
				pStmt.setObject(6, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(7, entity.getUpdated(), Types.TIMESTAMP);

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
	public void update(final ISupport entity) {
		executeStatement(new PreparedStatementAction<ISupport>(String.format(
				"update %s set problem_name=?, problem=?, status=?, user_cabinet_id=?, manager_id=?, updated=? where id=?",
				getTableName())) {
			@Override
			public ISupport doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getProblemName());
				pStmt.setString(2, entity.getProblem());
				pStmt.setBoolean(3, entity.getStatus());
				pStmt.setInt(4, entity.getUserCabinet().getId());
				pStmt.setInt(5, entity.getUserAccount().getId());
				pStmt.setObject(6, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(7, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected ISupport parseRow(final ResultSet resultSet) throws SQLException {
		final ISupport entity = createEntity();

		entity.setId((Integer) resultSet.getObject("id"));
		entity.setProblemName(resultSet.getString("problem_name"));
		entity.setProblem(resultSet.getString("problem"));
		entity.setStatus(resultSet.getBoolean("status"));

		final IUserCabinet userCabinet = new UserCabinet();
		userCabinet.setId((Integer) resultSet.getObject("user_cabinet_id"));
		entity.setUserCabinet(userCabinet);

		final IUserAccount userAccount = new UserAccount();
		userAccount.setId((Integer) resultSet.getObject("manager_id"));
		entity.setUserAccount(userAccount);

		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public List<ISupport> find(final SupportFilter filter) {
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public long getCount(final SupportFilter filter) {
		return executeCountQuery("");
	}

	@Override
	protected String getTableName() {
		return "support";
	}

	@Override
	public ISupport getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getSum(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
