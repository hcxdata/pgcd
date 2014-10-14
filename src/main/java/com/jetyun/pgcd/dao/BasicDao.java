package com.jetyun.pgcd.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jetyun.pgcd.dao.ibatis.ext.BaseSqlMapClientDaoSupport;
import com.jetyun.pgcd.system.page.Page;
import com.jetyun.pgcd.system.pageTag.Pagination;

public class BasicDao<T> extends BaseSqlMapClientDaoSupport {

	@SuppressWarnings("unchecked")
	public List<T> queryForList(String statementName, int offset, int limit) {
		return getSqlMapClientTemplate().queryForList(statementName, offset,
				limit);
	}

	@SuppressWarnings("unchecked")
	public <T> T queryForObject(String statementName, Object parameterObject) {
		return (T) getSqlMapClientTemplate().queryForObject(statementName,
				parameterObject);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> queryForList(String statementName, Object parameterObject) {
		return getSqlMapClientTemplate().queryForList(statementName,
				parameterObject);
	}

	public Pagination<T> getPage(String statementName, Integer page,
			int pageSize, Map<String, ?> params) {
		if (page == null) {
			page = 1;
		}

		int offset = (page - 1) * pageSize;
		List<T> list = this.queryForList(statementName, offset, pageSize);
		int count = (Integer) getSqlMapClientTemplate().queryForObject(
				statementName + "Count", params);
		return new Pagination<T>(page, pageSize, count, list);
	}

	public Pagination<T> getPage(String statementName, Integer page) {
		return this.getPage(statementName, page, Page.DEFAULT_PAGESIZE,
				new HashMap<String, Object>());
	}
	
	public <T> void insert(String statementName, T obj) {
		  getSqlMapClientTemplate().insert(statementName, obj);
	}
	
	public <T> void delete(String statementName, T obj) {
		getSqlMapClientTemplate().delete(statementName, obj);
	}
}
