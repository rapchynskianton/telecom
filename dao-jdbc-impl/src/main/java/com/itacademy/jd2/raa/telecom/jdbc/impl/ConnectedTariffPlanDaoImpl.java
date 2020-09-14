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

import com.itacademy.jd2.raa.telecom.dao.api.IConnectedTariffPlanDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IConnectedTariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.filter.ConnectedTariffPlanFilter;
import com.itacademy.jd2.raa.telecom.jdbc.impl.entity.ConnectedTariffPlan;
import com.itacademy.jd2.raa.telecom.jdbc.impl.entity.TariffPlan;
import com.itacademy.jd2.raa.telecom.jdbc.impl.util.SQLExecutionException;

@Repository
public class ConnectedTariffPlanDaoImpl extends AbstractDaoImpl<IConnectedTariffPlan, Integer>
		implements IConnectedTariffPlanDao {

	@Override
	public IConnectedTariffPlan createEntity() {
		return new ConnectedTariffPlan();
	}

	@Override
	public void insert(final IConnectedTariffPlan entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"insert into %s (tariff_plan_id, activation_date, sum_cost, created, updated) values(?,?,?,?,?)",
						getTableName()), Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {

				pStmt.setInt(1, entity.getTariffPlan().getId());
				pStmt.setObject(2, entity.getActivationDate(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getSumCost(), Types.DECIMAL);
				pStmt.setObject(4, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();
				entity.setId(id);
				// the same should be done in 'update' DAO method
				// updateEngines(entity, c);
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
	public void update(final IConnectedTariffPlan entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"update %s set tariff_plan_id=?, activation_date=?, sum_cost=?, updated=? where id=?",
						getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getTariffPlan().getId());
				pStmt.setObject(2, entity.getActivationDate(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getSumCost(), Types.DECIMAL);
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(5, entity.getId());

				pStmt.executeUpdate();
				// the same should be done in 'update' DAO method
				// pdateEngines(entity, c);
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
	protected IConnectedTariffPlan parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final IConnectedTariffPlan entity = createEntity();

		// ?????????????????????????????????????????
		final Integer tariffPlanId = (Integer) resultSet.getObject("tariff_plan_id");
		if (tariffPlanId != null) {
			final TariffPlan tariffPlan = new TariffPlan();
			tariffPlan.setId(tariffPlanId);
//			if (columns.contains("brand_name")) {
//				brand.setName(resultSet.getString("brand_name"));
//			}
			entity.setTariffPlan(tariffPlan);
		}

		entity.setId((Integer) resultSet.getObject("id"));
		entity.setActivationDate(resultSet.getTimestamp("activation_date"));
		entity.setSumCost(resultSet.getBigDecimal("sum_cost"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		return entity;
	}

	@Override
	public void deleteAll() {
		try (Connection c = getConnection();
				PreparedStatement deleteAttributeValueRefsStmt = c
						.prepareStatement("delete from tariff_plan_2_attribute_value");
				PreparedStatement deleteAllStmt = c.prepareStatement("delete from " + getTableName());) {
			c.setAutoCommit(false);
			try {
				deleteAttributeValueRefsStmt.executeUpdate();
				deleteAllStmt.executeUpdate();

				deleteAttributeValueRefsStmt.close();
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
	protected String getTableName() {
		return "connected_tariff_plan";
	}

	@Override
	public List<IConnectedTariffPlan> find(final ConnectedTariffPlanFilter filter) {
		return null;
	}

	@Override
	public long getCount(final ConnectedTariffPlanFilter filter) {
		return 0;
	}

	@Override
	public IConnectedTariffPlan getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getSum(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
