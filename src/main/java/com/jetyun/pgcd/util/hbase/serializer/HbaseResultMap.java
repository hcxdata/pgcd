package com.jetyun.pgcd.util.hbase.serializer;

import org.apache.hadoop.hbase.client.Result;

public interface HbaseResultMap<T> {
	public T getResutl(Result result) throws Exception;
}
