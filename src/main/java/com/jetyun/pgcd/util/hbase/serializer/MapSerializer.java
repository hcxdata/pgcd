package com.jetyun.pgcd.util.hbase.serializer;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;

import com.jetyun.pgcd.util.hbase.HRMTable;

/**
 * 将hbase行数据转换为map对象
 * 
 * @author hui_ease@163.com
 * 
 */
public class MapSerializer implements HbaseResultMap<Map<String, String>> {

	private Map<String, HRMTable.TYPES> typeMap;

	public MapSerializer() {
	}

	public MapSerializer(Map<String, HRMTable.TYPES> params) {
		typeMap = params;
	}

	@Override
	public Map<String, String> getResutl(Result r) {
		Map<String, String> map = new HashMap<String, String>();
		// 没有数据则停止检索
		if (r == null || r.list() == null || r.list().size() == 0) {
			return map;
		}
		// 存储rowKey信息
		map.put("rowkey", HRMTable.b2s(r.list().get(0).getRow()));
		// 存储字段信息
		for (KeyValue kv : r.list()) {
			String key = HRMTable.b2s(kv.getFamily()) + ":"
					+ HRMTable.b2s(kv.getQualifier());
			map.put(key,
					HRMTable.getByteValue(kv.getValue(), getType(typeMap, key)));
		}
		return map;
	}

	private boolean match(String regex, String content) {
		return Pattern.compile(regex).matcher(content).matches();
	}

	private HRMTable.TYPES getType(Map<String, HRMTable.TYPES> map, String key) {
		if (typeMap == null || typeMap.isEmpty())
			return HRMTable.TYPES.CHARTYPE;
		for (Map.Entry<String, HRMTable.TYPES> entry : map.entrySet()) {
			if (match(entry.getKey(), key)) {
				return entry.getValue();
			}
		}
		return HRMTable.TYPES.CHARTYPE;
	}
}
