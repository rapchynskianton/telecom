package com.itacademy.jd2.raa.telecom.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.raa.telecom.dao.api.IPassportDetailsDao;
import com.itacademy.jd2.raa.telecom.dao.api.IUserAccountDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.raa.telecom.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.raa.telecom.jdbc.impl.util.SQLExecutionException;

@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

	private IPassportDetailsDao passportDetailsDao;

	@Autowired
	public UserAccountDaoImpl(IPassportDetailsDao passportDetailsDao) {
		super();
		this.passportDetailsDao = passportDetailsDao;
	}

	@Override
	public IUserAccount createEntity() {
		return new UserAccount();
	}

	@Override
	public void insert(final IUserAccount entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"insert into %s (email, user_password, role, first_name, last_name, fathers_name, adress, telephone, created, updated) values(?,?,?,?,?,?,?,?,?,?)",
						getTableName()), Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getEmail());
				pStmt.setString(2, entity.getUserPassword());
				pStmt.setString(3, entity.getRole().name());
				pStmt.setString(4, entity.getFirstName());
				pStmt.setString(5, entity.getLastName());
				pStmt.setString(6, entity.getFathersName());
				pStmt.setString(7, entity.getAdress());
				pStmt.setString(8, entity.getTelephone());
				pStmt.setObject(9, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(10, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");
				rs.close();
				entity.setId(id);
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
	public void update(final IUserAccount entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"update %s set email=?, user_password=?, role=?, first_name=?, last_name=?, fathers_name=?, adress=?, telephone=?, created=?, updated=? where id=?",
						getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getEmail());
				pStmt.setString(2, entity.getUserPassword());
				pStmt.setString(3, entity.getRole().name());
				pStmt.setString(4, entity.getFirstName());
				pStmt.setString(5, entity.getLastName());
				pStmt.setString(6, entity.getFathersName());
				pStmt.setString(7, entity.getAdress());
				pStmt.setString(8, entity.getTelephone());
				pStmt.setObject(9, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(10, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(11, entity.getId());
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
	protected IUserAccount parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final IUserAccount entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setEmail(resultSet.getString("email"));
		entity.setUserPassword(resultSet.getString("user_password"));
		entity.setRole(UserRole.valueOf(resultSet.getString("role")));
		entity.setFirstName(resultSet.getString("first_name"));
		entity.setLastName(resultSet.getString("last_name"));
		entity.setFathersName(resultSet.getString("fathers_name"));
		entity.setAdress(resultSet.getString("adress"));
		entity.setTelephone(resultSet.getString("telephone"));
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
	public IUserAccount getFullInfo(final Integer id) {
		final IUserAccount model = get(id);

		model.setPassportDetails(passportDetailsDao.get(id));
		return model;
	}

	@Override
	protected String getTableName() {
		return "user_account";
	}

	@Override
	public List<IUserAccount> find(final UserAccountFilter filter) {

		return null;
	}

	@Override
	public long getCount(final UserAccountFilter filter) {

		return 0;
	}

	@Override
	public IUserAccount getUserAndEmail(String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IUserAccount getSearchUserAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getSum(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
