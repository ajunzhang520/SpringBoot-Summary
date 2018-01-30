package com.fiberhome.cus.hbase.api;

import java.io.IOException;

/**
 * Description:
 * @author sjZhang
 * @date 2017年12月22日上午9:31:28
 */
public interface HbaseOperations {
	public void getResultScann(String tableName) throws IOException;

}
