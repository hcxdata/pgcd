package com.jetyun.pgcd.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jetyun.pgcd.util.hbase.HRMTable;
import com.jetyun.pgcd.util.hbase.serializer.MapSerializer;

@Repository
public class CountAnalyzeDao {
	static Map<String, HRMTable.TYPES> typeMap = new HashMap<String, HRMTable.TYPES>();
	static {
		typeMap.put(".*", HRMTable.TYPES.LONGTYPE);
	}

	/**
	 * 获取微博总数
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getWeiboCount() throws IOException {
		HRMTable table = new HRMTable("hntd_collect_statistics");
		Map<String, String> value = table.getRowValues(
				"hntd_collect_weibo_realtime", typeMap);
		return value.get("statistics:value");
	}

	/**
	 * 获取用户总数
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getUserCount() throws IOException {
		HRMTable table = new HRMTable("hntd_collect_statistics");
		Map<String, String> value = table.getRowValues(
				"hntd_collect_weibo_user", typeMap);
		return value.get("statistics:value");
	}

	/**
	 * 获取某天的微博总数和用户总数
	 * 
	 * @param day
	 *            日期，格式为yyyyMMdd
	 * @return
	 * @throws IOException
	 */
	public String getDayWeiboCount(String day) throws IOException {
		return getDayKeyWordWeibos(day).add(getDayUserWeibos(day)).toString();
	}

	/**
	 * 获取某天关键字采集的所有微博总数
	 * 
	 * @param day
	 * @return
	 * @throws IOException
	 */
	public BigDecimal getDayKeyWordWeibos(String day) throws IOException {
		return getCountValueWithStartAndEnd("hntd_collect_weibo_realtime" + "_"
				+ day, "hntd_collect_weibo_realtime_" + day + "_%~");
	}

	/**
	 * 获取某天关注账号采集的微博总数
	 * 
	 * @param day
	 * @return
	 * @throws IOException
	 */
	public BigDecimal getDayUserWeibos(String day) throws IOException {
		return getCountValueWithStartAndEnd("hntd_collect_weibo_user_weibo"
				+ "_" + day, "hntd_collect_weibo_user_weibo" + "_" + day
				+ "_%~");
	}

	/**
	 * 获取某天某个关键字采集的用户总数
	 * 
	 * @param day
	 * @param keyWord
	 * @return
	 * @throws IOException
	 */
	public BigDecimal getDayWithKeyWordWeibos(String day, String keyWord)
			throws IOException {
		String rowKey = "hntd_collect_weibo_realtime" + "_" + day + "_"
				+ java.net.URLEncoder.encode(keyWord, "utf8");
		HRMTable table = new HRMTable("hntd_collect_statistics");
		BigDecimal bgvalue = new BigDecimal("0");
		Map<String, String> value = table.getRowValues(rowKey, typeMap);
		for (int i = 0; i < 24; i++) {
			String val = value.get("statistics:value_"
					+ StringUtils.leftPad("" + i, 2, "0"));
			if (StringUtils.isNumeric(val))
				bgvalue = bgvalue.add(new BigDecimal(val));
		}
		return bgvalue;
	}

	/**
	 * 获取某天采集的用户总数
	 * 
	 * @param day
	 * @return
	 * @throws IOException
	 */
	public BigDecimal getDayUsers(String day) throws IOException {
		String rowKey = "hntd_collect_weibo_user" + "_" + day;
		HRMTable table = new HRMTable("hntd_collect_statistics");
		BigDecimal bgvalue = new BigDecimal("0");
		Map<String, String> value = table.getRowValues(rowKey, typeMap);
		for (int i = 0; i < 24; i++) {
			String val = value.get("statistics:value_"
					+ StringUtils.leftPad("" + i, 2, "0"));
			if (StringUtils.isNumeric(val))
				bgvalue = bgvalue.add(new BigDecimal(val));
		}
		return bgvalue;
	}

	/**
	 * 获取指定startrowkey和endrowkey间的数据
	 * 
	 * @param startRow
	 * @param endRow
	 * @return
	 * @throws IOException
	 */
	public static BigDecimal getCountValueWithStartAndEnd(String startRow,
			String endRow) throws IOException {
		HRMTable table = new HRMTable("hntd_collect_statistics");
		BigDecimal bgvalue = new BigDecimal("0");
		List<Map<String, String>> values = table.getRows(startRow, endRow,
				null, 0, -1, new MapSerializer(typeMap));
		for (Map<String, String> value : values) {
			for (Map.Entry<String, String> valEntry : value.entrySet()) {
				if (valEntry.getKey().contains("rowkey")) {
					continue;
				}
				String val = valEntry.getValue();
				if (StringUtils.isNumeric(val))
					bgvalue = bgvalue.add(new BigDecimal(val));
			}
		}
		return bgvalue;
	}

}
