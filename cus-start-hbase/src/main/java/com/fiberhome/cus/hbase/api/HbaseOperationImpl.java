package com.fiberhome.cus.hbase.api;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;

public class HbaseOperationImpl implements HbaseOperations{
	
	@Autowired
	private Configuration conf;

	@Override
	public void getResultScann(String tableName) throws IOException {
		Connection conn = ConnectionFactory.createConnection(conf);
		Table table = conn.getTable(TableName.valueOf(tableName));
		Scan scan = new Scan();
		ResultScanner rs = null;
		try {
			rs = table.getScanner(scan);
			for (Result r : rs) {
				for (Cell cell : r.rawCells()) {
					System.out.println("family:" + Bytes.toString(CellUtil.cloneFamily(cell)));
					System.out.println("qualifier:" + Bytes.toString(CellUtil.cloneQualifier(cell)));
					System.out.println("value:" + Bytes.toString(CellUtil.cloneValue(cell)));
					System.out.println("-------------------------------------------");
				}
			}
		} finally {
			if(null != rs){
				rs.close();
			}
			if(null != table){
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != conn){
				try {
					conn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

}
