package com.jetyun.pgcd.dao.ibatis.ext.dialect;

import org.springframework.stereotype.Service;

/**
 * 类说明:自动方言识别类<br>
 * 创建时间: 2012-12-26 下午04:11:07<br>
 * 
 * @author 李祥辉<br>
 * @email lixh@neteast.com<br>
 */
@Service
public class AutoDialect implements Dialect {
	private static final String SQL_END_DELIMITER = ";";

	public String getLimitString(String dbName, String sql, int offset,
			int limit) {
		String limitString = sql;
		if (dbName.toLowerCase().indexOf("mysql") != -1) {
			limitString = getMysqlLimitString(sql, offset, limit);
		}
		if (dbName.toLowerCase().indexOf("microsoft sql server") != -1) {
			limitString = getMssqlLimitString(sql, offset, limit);
		}
		if (dbName.toLowerCase().indexOf("oracle") != -1) {
			limitString = getOracleLimitString(sql, offset, limit);
		}
		if (dbName.toLowerCase().indexOf("db2") != -1) {
			limitString = getDB2LimitString(sql, offset, limit);
		}

		return limitString;
	}

	private static String getMysqlLimitString(String sql, int offset, int limit) {
		sql = trim(sql);
		StringBuffer sb = new StringBuffer(sql.length() + 50);
		if (sql.indexOf("limit") > -1) {
			sb.append("select * from ( ");
			sb.append(sql);
			sb.append(" ) ibatisLimit ");
		} else {
			sb.append(sql);
		}
		if (offset > 0) {
			sb.append(" limit ").append(offset).append(',').append(limit);
		} else {
			sb.append(" limit ").append(limit);
		}
		return sb.toString();
	}

	private static String getOracleLimitString(String sql, int offset, int limit) {
		sql = trim(sql);
		StringBuffer sb = new StringBuffer(sql.length() + 100);
		if (offset > 0) {
			sb.append("select * from ( select row_.*, rownum rownum_ from ( ")
					.append(sql).append(" ) row_ where rownum <= ")
					.append(offset + limit).append(") where rownum_ > ")
					.append(offset);
		} else {
			sb.append("select * from ( ").append(sql)
					.append(" ) where rownum <= ").append(limit);
		}
		return sb.toString();
	}

	private static String getMssqlLimitString(String sql, int offset, int limit) {
		return null;
	}

	private static String getDB2LimitString(String sql, int offset, int limit) {
		return null;
	}

	private static String trim(String sql) {
		sql = sql.trim();
		if (sql.endsWith(SQL_END_DELIMITER)) {
			sql = sql.substring(0,
					sql.length() - 1 - SQL_END_DELIMITER.length());
		}
		return sql;
	}
}