package com.jetyun.pgcd.util.hbase.serializer;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;

import com.jetyun.pgcd.util.hbase.HRMTable;

/**
 * 将hbase行数据转换为object对象
 * 
 * @author hui_ease@163.com
 * 
 */
public class ObjectSerializer implements HbaseResultMap {
	private Object t;
	private BeanInfo beanInfo;

	public ObjectSerializer(Class c) throws IntrospectionException,
			InstantiationException, IllegalAccessException {
		this.t = c.newInstance();
		beanInfo = Introspector.getBeanInfo(c, Object.class);
	}

	@Override
	public Object getResutl(Result r) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		if (r == null || r.list() == null || r.list().size() == 0) {
			return t;
		}
		PropertyDescriptor props[] = beanInfo.getPropertyDescriptors();
		// 设置key
		for (int i = 0; i < props.length; i++) {
			if (props[i].getName().equals("rowKey")) {
				if (props[i].getWriteMethod() != null) {
					Method method = props[i].getWriteMethod();
					method.invoke(t, HRMTable.b2s(r.list().get(0).getRow()));
				}
			}
		}
		// 设置值
		for (KeyValue kv : r.list()) {
			String key = HRMTable.b2s(kv.getFamily()) + "$"
					+ HRMTable.b2s(kv.getQualifier());
			for (int i = 0; i < props.length; i++) {
				if (props[i].getName().equals(key)) {
					if (props[i].getWriteMethod() != null) {
						Class proType = props[i].getPropertyType();
						Method method = props[i].getWriteMethod();
						method.invoke(t,
								(HRMTable.getByteValue(kv.getValue(), proType)));
					}
				}
			}
		}
		return t;
	}
}
