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

import com.itacademy.jd2.raa.telecom.dao.api.IAttributeValueDao;
import com.itacademy.jd2.raa.telecom.dao.api.ITariffPlanDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.filter.TariffPlanFilter;
import com.itacademy.jd2.raa.telecom.jdbc.impl.entity.TariffPlan;
import com.itacademy.jd2.raa.telecom.jdbc.impl.util.SQLExecutionException;

@Repository
public class TariffPlanDaoImpl extends AbstractDaoImpl<ITariffPlan, Integer> implements ITariffPlanDao {
	private IAttributeValueDao attributeValueDao;

	@Autowired
	public TariffPlanDaoImpl(IAttributeValueDao attributeValueDao) {
		super();
		this.attributeValueDao = attributeValueDao;
	}

	@Override
	public ITariffPlan createEntity() {
		return new TariffPlan();
	}

	@Override
	public void insert(final ITariffPlan entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"insert into %s (name, cost_per_unit, unit, type, created, updated) values(?,?,?,?,?,?)",
						getTableName()), Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {

				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getCostPerUnit(), Types.DECIMAL);
				pStmt.setInt(3, entity.getUnit());
				pStmt.setString(4, entity.getType());
				pStmt.setObject(5, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(6, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();
				entity.setId(id);
				// the same should be done in 'update' DAO method
				updateAttributeValue(entity, c);
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
	public void update(final ITariffPlan entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("update %s set name=?, cost_per_unit=?, unit=?, type=?, updated=? where id=?",
								getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getCostPerUnit(), Types.DECIMAL);
				pStmt.setInt(3, entity.getUnit());
				pStmt.setString(4, entity.getType());
				pStmt.setObject(5, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setInt(6, entity.getId());
				pStmt.executeUpdate();

				// the same should be done in 'update' DAO method
				updateAttributeValue(entity, c);
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
	protected ITariffPlan parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final ITariffPlan entity = createEntity();

		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCostPerUnit(resultSet.getBigDecimal("cost_per_unit"));
		entity.setUnit(resultSet.getInt("unit"));
		entity.setType(resultSet.getString("type"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		return entity;
	}

	@Override
	public void deleteAll() {
		try (Connection c = getConnection();
				PreparedStatement deleteAttributeRefsStmt = c
						.prepareStatement("delete from tariff_plan_2_attribute_value");
				PreparedStatement deleteAllStmt = c.prepareStatement("delete from " + getTableName());) {
			c.setAutoCommit(false);
			try {
				deleteAttributeRefsStmt.executeUpdate();
				deleteAllStmt.executeUpdate();

				deleteAttributeRefsStmt.close();
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
		return "tariff_plan";
	}

	@Override
	public ITariffPlan getFullInfo(final Integer id) {

		final ITariffPlan tariffPlan = get(id);

		return tariffPlan;
	}

	private void updateAttributeValue(final ITariffPlan entity, final Connection c) throws SQLException {
		// clear all existing records related to the current model
		final PreparedStatement deleteStmt = c
				.prepareStatement("delete from tariff_plan_2_attribute_value where tariff_plan_id=?");
		deleteStmt.setInt(1, entity.getId());
		deleteStmt.executeUpdate();
		deleteStmt.close();

		if (entity.getAttributeValue().isEmpty()) {
			return;
		}

		// insert actual list of records using 'batch'
		final PreparedStatement pStmt = c.prepareStatement(
				"insert into tariff_plan_2_attribute_value (tariff_plan_id, attribute_value_id) values(?,?)");

		for (final IAttributeValue e : entity.getAttributeValue()) {
			pStmt.setInt(1, entity.getId());
			pStmt.setInt(2, e.getId());
			pStmt.addBatch();
		}
		pStmt.executeBatch();
		pStmt.close();
	}

	@Override
	public List<ITariffPlan> find(final TariffPlanFilter filter) {
//		final StringBuilder sql;
//		if (filter.getFetchBrand()) {
//			sql = new StringBuilder(String.format("select tariff_plan.*, brand.name as brand_name from %s", getTableName()));
//		} else {
//			sql = new StringBuilder(String.format("select model.* from %s", getTableName()));
//		}
//		appendJOINs(sql, filter);
//		appendWHEREs(sql, filter);
//		appendSort(filter, sql);
//		appendPaging(filter, sql);
//		return executeFindQueryWithCustomSelect(sql.toString());
		return null;
	}

	@Override
	public long getCount(final TariffPlanFilter filter) {
//		final StringBuilder sql = new StringBuilder("");
//		appendJOINs(sql, filter);
//		appendWHEREs(sql, filter);
//		return executeCountQuery(sql.toString());
		return 0;

	}

	@Override
	public Integer getSum(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

//	private void appendJOINs(final StringBuilder sb, final TariffPlanFilter filter) {
//		if (filter.getFetchBrand()) {
//			sb.append(" join brand on (brand.id=model.brand_id) ");
//		}
//	}

}
