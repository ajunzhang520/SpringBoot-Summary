package com.fiberhome.cus.hbase.config;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.fiberhome.cus.hbase.api.HbaseOperationImpl;
import com.fiberhome.cus.hbase.api.HbaseOperations;

/**
 * Description:env对应的配置应置于配置中心最外层即Application.properties文件中
 * @author sjZhang
 * @date 2017年12月22日上午9:26:46
 */
@Configuration
public class HbaseConfig {
	
//	@Autowired
//	private Environment env;

	// 1.初始化HBaseOperation
	@Bean
	public org.apache.hadoop.conf.Configuration initConfiguration() {
//		String quorum = env.getProperty("hbase.zookeeper.quorum", "");
//		String port = env.getProperty("hbase.zookeeper.port", "2181");
//		String znodeParent = env.getProperty("zookeeper.znode.parent", "");
		org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
		configuration.set(HConstants.ZOOKEEPER_QUORUM, "10.110.200.161,10.110.200.162,10.110.200.163");
		//configuration.set(HConstants.ZOOKEEPER_CLIENT_PORT, "2181");
		configuration.set(HConstants.ZOOKEEPER_ZNODE_PARENT, "/hbase-unsecure");
		org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create(configuration);
		return conf;
	}
	
	@Bean
	public HbaseOperations hbaseOperations(){
		return new HbaseOperationImpl();
	}

}
