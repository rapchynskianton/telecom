package com.itacademy.jd2.raa.telecom.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.raa.telecom.dao.api.IUserCabinetDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IBranch;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.dao.api.filter.UserCabinetFilter;
import com.itacademy.jd2.raa.telecom.jdbc.impl.entity.Branch;
import com.itacademy.jd2.raa.telecom.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.raa.telecom.jdbc.impl.entity.UserCabinet;
import com.itacademy.jd2.raa.telecom.jdbc.impl.util.PreparedStatementAction;
import com.itacademy.jd2.raa.telecom.jdbc.impl.util.SQLExecutionException;

@Repository
public class UserCabinetDaoImpl extends AbstractDaoImpl<IUserCabinet, Integer> implements IUserCabinetDao {

	@Override
	public IUserCabinet createEntity() {
		return new UserCabinet();
	}

	@Override
	public void insert(final IUserCabinet entity) {
		executeStatement(new PreparedStatementAction<IUserCabinet>(String.format(
				"insert into %s (activation_date, status, user_account_id, branch_id, created, updated) values(?,?,?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public IUserCabinet doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {

				pStmt.setObject(1, entity.getActivationDate(), Types.TIMESTAMP);
				pStmt.setBoolean(2, entity.getStatus());
				pStmt.setInt(3, entity.getUserAccount().getId());
				pStmt.setInt(4, entity.getBranch().getId());
				pStmt.setObject(5, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(6, entity.getUpdated(), Types.TIMESTAMP);

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
	public void update(final IUserCabinet entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"update %s set activation_date, status, user_account_id, branch_id, updated where id=?",
						getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setObject(1, entity.getActivationDate(), Types.TIMESTAMP);
				pStmt.setBoolean(2, entity.getStatus());
				pStmt.setInt(3, entity.getUserAccount().getId());
				pStmt.setInt(4, entity.getBranch().getId());
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(6, entity.getId());
				pStmt.executeUpdate();
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
	protected IUserCabinet parseRow(final ResultSet resultSet) throws SQLException {
		final IUserCabinet entity = createEntity();

		entity.setId((Integer) resultSet.getObject("id"));
		entity.setStatus(resultSet.getBoolean("status"));

		final IUserAccount userAccount = new UserAccount();
		userAccount.setId((Integer) resultSet.getObject("user_account_id"));
		entity.setUserAccount(userAccount);

		final IBranch branch = new Branch();
		branch.setId((Integer) resultSet.getObject("branch_id"));
		entity.setBranch(branch);

		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		return entity;
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
	public List<IUserCabinet> find(final UserCabinetFilter filter) {
		return null;
	}

	@Override
	public long getCount(final UserCabinetFilter filter) {
		return executeCountQuery("");
	}

	@Override
	protected String getTableName() {
		return "user_cabinet";
	}

	@Override
	public IUserCabinet getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getSum(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
