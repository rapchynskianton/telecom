package com.itacademy.jd2.raa.telecom.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.raa.telecom.dao.api.IPassportDetailsDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IPassportDetails;
import com.itacademy.jd2.raa.telecom.jdbc.impl.entity.PassportDetails;
import com.itacademy.jd2.raa.telecom.jdbc.impl.util.PreparedStatementAction;

@Repository
public class PassportDetailsDaoImpl extends AbstractDaoImpl<IPassportDetails, Integer> implements IPassportDetailsDao {

	@Override
	public IPassportDetails createEntity() {
		return new PassportDetails();
	}

	@Override
	public void update(IPassportDetails entity) {
		executeStatement(new PreparedStatementAction<IPassportDetails>(String.format(
				"update %s set serial=?, serial_number=?, date_of_issue=?, identification_number=?, passport_authority=?, updated=? where id=?",
				getTableName())) {
			@Override
			public IPassportDetails doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getSerial());
				pStmt.setString(2, entity.getSerialNumber());
				pStmt.setObject(3, entity.getDateOfIssue(), Types.TIMESTAMP);
				pStmt.setString(4, entity.getIdentificationNumber());
				pStmt.setString(5, entity.getPassportAuthority());
				pStmt.setObject(6, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(7, entity.getId());
				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(IPassportDetails entity) {
		executeStatement(new PreparedStatementAction<IPassportDetails>(String.format(
				"insert into %s (id, serial, serial_number, date_of_issue, identification_number, passport_authority, created, updated) values(?,?,?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public IPassportDetails doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {

				pStmt.setInt(1, entity.getId());
				pStmt.setString(2, entity.getSerial());
				pStmt.setString(3, entity.getSerialNumber());
				pStmt.setObject(4, entity.getDateOfIssue(), Types.TIMESTAMP);
				pStmt.setString(5, entity.getIdentificationNumber());
				pStmt.setString(6, entity.getPassportAuthority());
				pStmt.setObject(7, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(8, entity.getUpdated(), Types.TIMESTAMP);

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
	protected IPassportDetails parseRow(final ResultSet resultSet) throws SQLException {
		final IPassportDetails entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setSerial(resultSet.getString("serial"));
		entity.setSerialNumber(resultSet.getString("serial_number"));
		entity.setDateOfIssue(resultSet.getTimestamp("date_of_issue"));
		entity.setIdentificationNumber(resultSet.getString("identification_number"));
		entity.setPassportAuthority(resultSet.getString("passport_authority"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "passport_details";
	}

	@Override
	public Integer getSum(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
