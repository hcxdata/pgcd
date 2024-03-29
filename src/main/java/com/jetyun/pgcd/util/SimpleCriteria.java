package com.jetyun.pgcd.util;

import java.util.List;

/**
 * 条件查询类
 */
public class SimpleCriteria implements Criteria {
	private List<String> fields;
	/** 要排序的字段 */
	private List<String> order;
	/** 条件查询子类 */
	private List<Condition> condition;
	/** 当前页数 */
	private int pageNo = 1;
	/** 第一条记录 */
	private int start;
	/** 最大记录数 */
	private int fetchSize = 10;

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	public List<Condition> getCondition() {
		return condition;
	}

	public void setCondition(List<Condition> condition) {
		this.condition = condition;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getFetchSize() {
		return fetchSize;
	}

	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}

	public List<String> getOrder() {
		return order;
	}

	public void setOrder(List<String> order) {
		this.order = order;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public String toString() {
		StringBuffer clause = new StringBuffer();
		List<Condition> l = this.getCondition();
		if (l != null) {
			Condition c1;
			// 循环读取条件对象
			int size = l.size();

			// 如果size > 1, 先构造第一个where子句
			if (size > 0) {
				c1 = l.get(0);
				clause.append(" where ");
				clause.append(c1.getField());
				clause.append(" ");
				clause.append(c1.getRelation());
				clause.append(" ");
				clause.append(c1.getValue());
			}

			// 循环从1开始
			for (int i = 1; i < size; i++) {
				c1 = l.get(i);

				// 直接构造where条件
				clause.append(" ");
				clause.append(c1.getType());
				clause.append(" ");
				clause.append(c1.getField());
				clause.append(" ");
				clause.append(c1.getRelation());
				clause.append(" ");
				clause.append(c1.getValue());
			}
		}

		if (this.getOrder() != null) {
			clause.append(" ORDER BY ");
			for (int i = 0; i < this.getOrder().size(); i++) {
				clause.append(this.getOrder().get(i));
				clause.append(",");
			}
		}

		String sql = clause.toString();

		if (sql.endsWith(","))
			sql = sql.substring(0, sql.length() - 1);
		return sql;
	}
}