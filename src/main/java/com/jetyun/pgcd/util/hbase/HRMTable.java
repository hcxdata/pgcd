package com.jetyun.pgcd.util.hbase;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableExistsException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.impl.Log4jLoggerFactory;

import com.jetyun.pgcd.util.Condition;
import com.jetyun.pgcd.util.hbase.serializer.HbaseResultMap;
import com.jetyun.pgcd.util.hbase.serializer.MapSerializer;
import com.jetyun.pgcd.util.hbase.serializer.ObjectSerializer;

/**
 * 将hbase转换为普通关系数据库表的操作类
 * 
 * @author Administrator
 * 
 */
public class HRMTable {
	public static Configuration configuration = HBaseConfiguration.create();
	public static Logger logger = new Log4jLoggerFactory()
			.getLogger("HRMTable");

	public static enum TYPES {
		BYTETYPE, SHORTTYPE, INTTYPE, LONGTYPE, FLOATTYPE, DOUBLETYPE, BOOLEANTYPE, CHARTYPE
	};

	private HTableInterface table = null;

	public static byte[] s2b(String o) {
		if (o == null)
			return null;
		return Bytes.toBytes(o);
	}

	public static String b2s(byte[] bs) {
		if (bs == null)
			return null;
		return Bytes.toString(bs);
	}

	/**
	 * 获取bytes对应的java对象
	 * 
	 * @param bs
	 * @param type
	 * @return
	 */
	public static String getByteValue(byte[] bs, TYPES type) {
		switch (type) {
		case BYTETYPE:
			return new String(bs);
		case SHORTTYPE:
			return String.valueOf(Bytes.toShort(bs));
		case INTTYPE:
			return String.valueOf(Bytes.toInt(bs));
		case LONGTYPE:
			return String.valueOf(Bytes.toLong(bs));
		case FLOATTYPE:
			return String.valueOf(Bytes.toFloat(bs));
		case DOUBLETYPE:
			return String.valueOf(Bytes.toDouble(bs));
		case BOOLEANTYPE:
			return String.valueOf(Bytes.toBoolean(bs));
		case CHARTYPE:
			return Bytes.toString(bs);
		}
		return b2s(bs);
	}

	/**
	 * 获取bytes对应的java对象
	 * 
	 * @param bs
	 * @param cla
	 * @return
	 */
	public static Object getByteValue(byte[] bs, Class cla) {
		if (cla == String.class)
			return new String(bs);
		else if (cla == Short.class)
			return Bytes.toShort(bs);
		else if (cla == Integer.class)
			return Bytes.toInt(bs);
		else if (cla == Long.class)
			return Bytes.toLong(bs);
		else if (cla == Float.class)
			return Bytes.toFloat(bs);
		else if (cla == Double.class)
			return Bytes.toDouble(bs);
		else if (cla == Boolean.class)
			return Bytes.toBoolean(bs);
		else
			return Bytes.toString(bs);
	}

	/**
	 * 获取bytes对应的java对象
	 * 
	 * @param bs
	 * @param cla
	 * @return
	 */
	public static byte[] getObjByteValue(Object obj) {
		if (obj.getClass() == String.class)
			return Bytes.toBytes((String) obj);
		else if (obj.getClass() == Short.class)
			return Bytes.toBytes((Short) obj);
		else if (obj.getClass() == Integer.class)
			return Bytes.toBytes((Integer) obj);
		else if (obj.getClass() == Long.class)
			return Bytes.toBytes((Long) obj);
		else if (obj.getClass() == Float.class)
			return Bytes.toBytes((Float) obj);
		else if (obj.getClass() == Double.class)
			return Bytes.toBytes((Double) obj);
		else if (obj.getClass() == Boolean.class)
			return Bytes.toBytes((Boolean) obj);
		else
			return Bytes.toBytes(obj.toString());
	}

	public HRMTable() {

	}

	/**
	 * 表名称
	 * 
	 * @param tableName
	 * @throws IOException
	 */
	public HRMTable(String tableName) throws IOException {
		HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
		// 是否存在表
		if (hBaseAdmin.tableExists(tableName)) {
			HTablePool pool = new HTablePool(configuration, 1000);
			table = pool.getTable(tableName);
		}
	}

	/**
	 * 判定表是否存在
	 * 
	 * @return
	 * @throws IOException
	 * @throws ZooKeeperConnectionException
	 * @throws MasterNotRunningException
	 */
	public boolean exists() throws IOException {
		return new HBaseAdmin(configuration).tableExists(b2s(table
				.getTableName()));
	}

	/**
	 * 创建表
	 * 
	 * @param tableName
	 *            表名
	 * @param rowNames
	 *            列组
	 * @throws IOException
	 */
	public void create(String tableName, String... rowNames) throws IOException {
		HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
		// 是否存在表
		if (hBaseAdmin.tableExists(tableName)) {
			throw new TableExistsException("table " + tableName
					+ "  already exists");
		} else {
			// 表结构
			HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
			// 添加列族
			for (String rowName : rowNames) {
				tableDescriptor.addFamily(new HColumnDescriptor(rowName));
			}
			hBaseAdmin.createTable(tableDescriptor);
			logger.info("have create table : " + tableName);
		}
	}

	/**
	 * 删除表
	 * 
	 * @param tableName
	 * @throws IOException
	 */
	public void drop(String tableName) throws IOException {
		HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
		// 是否存在表
		// 是否存在表
		if (hBaseAdmin.tableExists(tableName)) {
			// 先禁用
			hBaseAdmin.disableTable(tableName);
			// 后删除
			hBaseAdmin.deleteTable(tableName);
			logger.info(tableName + " is exist,have deleted.");
		} else {
			logger.warn("" + tableName + " is not exist");
		}
	}

	/**
	 * 更新表数据
	 * 
	 * @param rowKey
	 *            主键
	 * @param cf
	 *            列组名称
	 * @param key
	 *            列名称
	 * @param value
	 *            结果值
	 * @throws IOException
	 */
	public void put(String rowKey, String cf, String key, Object value)
			throws IOException {
		Put put = new Put(Bytes.toBytes(rowKey));
		put.add(Bytes.toBytes(cf), Bytes.toBytes(key), getObjByteValue(value));
		table.put(put);
	}

	/**
	 * 删除一行记录
	 */
	public void delRecord(String rowKey) throws IOException {
		Delete del = new Delete(rowKey.getBytes());
		table.delete(del);
	}

	/**
	 * 通过表名，列族和列获取唯一值
	 * 
	 * @param rowkey
	 *            列值
	 * @param family
	 *            列族
	 * @param qualifier
	 *            列
	 * @return
	 * @throws IOException
	 */
	public byte[] getValue(String rowkey, String family, String qualifier)
			throws IOException {
		Get get = new Get(s2b(rowkey));
		get.addColumn(s2b(family), s2b(qualifier));
		return table.get(get).value();
	}

	/**
	 * 通过表名，列族和列获取唯一值
	 * 
	 * @param rowkey
	 *            列值
	 * @param family
	 *            列族
	 * @param qualifier
	 *            列
	 * @return
	 * @throws IOException
	 */
	public String getStrValue(String rowkey, String family, String qualifier)
			throws IOException {
		return b2s(getValue(rowkey, family, qualifier));
	}

	/**
	 * 通过rowkey获取行信息无需类型转换
	 * 
	 * @param rowkey
	 * @return
	 * @throws IOException
	 */
	public Map<String, String> getRowValues(String rowkey) throws IOException {
		return getRowValues(rowkey, new HashMap());
	}

	/**
	 * 通过rowkey获取行信息
	 * 
	 * @param rowkey
	 * @param types
	 *            - 字段转换类型
	 * @return
	 * @throws IOException
	 */
	public Map<String, String> getRowValues(String rowkey,
			Map<String, TYPES> types) throws IOException {
		Get get = new Get(s2b(rowkey));
		MapSerializer resMap = new MapSerializer(types);
		return resMap.getResutl(table.get(get));
	}

	/**
	 * 通过rowkey获取行信息
	 * 
	 * @param rowkey
	 * @param obj
	 *            - 转换的对象
	 * @return
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IntrospectionException
	 * @throws InstantiationException
	 */
	public <T> T getRowValues(String rowkey, Class obj) throws IOException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, IntrospectionException,
			InstantiationException {
		Get get = new Get(s2b(rowkey));
		ObjectSerializer resMap = new ObjectSerializer(obj);
		return (T) resMap.getResutl(table.get(get));
	}

	/**
	 * 从hbase中获取行信息
	 * 
	 * @param startRowKey
	 *            - 开始的rowkey
	 * @param cons
	 *            - 查询条件
	 * @param start
	 *            - 开始行
	 * @param limit
	 *            -获取行数 -1为获取全部
	 * @return
	 */
	public List<Map<String, String>> getRows(String startRowKey,
			String stopRowKey, List<Condition> cons, Integer start,
			Integer limit) {
		return getRows(startRowKey, stopRowKey, cons, start, limit,
				new MapSerializer());
	}

	/**
	 * 从hbase中获取行信息
	 * 
	 * @param startRowKey
	 *            - 开始的rowkey
	 * @param cons
	 *            - 查询条件
	 * @param start
	 *            - 开始行
	 * @param limit
	 *            -获取行数 -1为获取全部
	 * @param c
	 *            -转换为的对象类型
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public <T> List<T> getRows(String startRowKey, String stopRowKey,
			List<Condition> cons, Integer start, Integer limit, Class<T> c)
			throws IntrospectionException, InstantiationException,
			IllegalAccessException {
		return getRows(startRowKey, stopRowKey, cons, start, limit,
				new ObjectSerializer(c));
	}

	/**
	 * 从hbase中获取行信息
	 * 
	 * @param startRowKey
	 *            - 开始的rowkey
	 * @param endRowKey
	 *            - 结束的rowkey
	 * @param cons
	 *            - 查询条件
	 * @param start
	 *            - 开始行
	 * @param types
	 *            - 需要转换的类型
	 * @param limit
	 *            -获取行数 -1为获取全部
	 * @return
	 */
	public List getRows(String startRowKey, String endRowKey,
			List<Condition> cons, Integer start, Integer limit,
			HbaseResultMap resMap) {
		List res = new ArrayList();
		Scan scan = new Scan();
		scan.setCaching(limit);
		ResultScanner rs = null;
		try {
			// 检索的开始行
			if (startRowKey != null)
				scan.setStartRow(s2b(startRowKey));
			// 检索的结束行
			if (endRowKey != null)
				scan.setStopRow(s2b(endRowKey));
			// 添加筛选条件
			FilterList filterList = getFilters(cons);
			// 筛选行数
			if (limit != -1) {
				if (start != null)
					limit = start + limit;
				Filter filter = new PageFilter(limit);
				filterList.addFilter(filter);
			}
			scan.setFilter(filterList);

			rs = table.getScanner(scan);
			int index = -1;
			for (Result r : rs) {
				index++;
				if (index < start)
					continue;
				res.add(resMap.getResutl(r));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (rs != null)
				rs.close();
		}
		return res;
	}

	/**
	 * 转换过滤条件为hbase的过滤条件
	 * 
	 * @param cons
	 *            - 远查询条件
	 * @return
	 */
	private FilterList getFilters(List<Condition> cons) {
		FilterList filterList = new FilterList();

		if (cons != null && cons.size() > 0) {
			for (Condition con : cons) {
				String familyStr = con.getField().split(":")[0];
				String qualifierStr = con.getField().split(":")[1];

				CompareOp op = caseRelation(con.getRelation());
				String value = con.getValue();
				Filter filter = null;
				if (con.getRelation().equals("like")) {
					RegexStringComparator comp = new RegexStringComparator(
							value);
					filter = new SingleColumnValueFilter(s2b(familyStr),
							s2b(qualifierStr), CompareOp.EQUAL, comp);
				} else {
					filter = new SingleColumnValueFilter(s2b(familyStr),
							s2b(qualifierStr), op, s2b(value));
				}
				filterList.addFilter(filter);
			}
		}

		return filterList;
	}

	private CompareOp caseRelation(String relation) {
		if (relation.equals("=") || relation.equals("like"))
			return CompareOp.EQUAL;
		if (relation.equals("<"))
			return CompareOp.LESS;
		if (relation.equals("<="))
			return CompareOp.LESS_OR_EQUAL;
		if (relation.equals("!="))
			return CompareOp.NOT_EQUAL;
		if (relation.equals(">"))
			return CompareOp.GREATER;
		if (relation.equals(">="))
			return CompareOp.GREATER_OR_EQUAL;
		return CompareOp.NO_OP;
	}

	public static void main(String[] args) throws IOException {

		HRMTable tb = new HRMTable("hntd_collect_weibo_realtime");
		String[] keys = new String[] { "台风预警","火灾","暴恐","空难","疫情","工厂爆炸","恐怖袭击","事故", "惨案", "犯罪", "周永康", "新华视点", "杀人",
				"受贿", "贪污", "地震" };
		for (String key : keys) {
			System.out.println("关键字：" + key+"===========");
			List<Condition> conlist = new ArrayList<Condition>();
			Map<String, TYPES> map = new HashMap<String, TYPES>();
			Condition con = new Condition();
			con.setField("weibo_info:keyword");
			con.setRelation("=");
			con.setValue(key);
			conlist.add(con);

			map.put(".*", TYPES.CHARTYPE);

			List<Map<String, String>> list = tb.getRows(null, null, conlist, 0,
					10, new MapSerializer(map));

			for (Map<String, String> mp : list) {
				System.out.println(mp.get("weibo_info:text"));

			}

		}
	}

}