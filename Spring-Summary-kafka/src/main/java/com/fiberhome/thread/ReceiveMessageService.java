package com.fiberhome.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fiberhome.utils.Lock;
import com.fiberhome.utils.LockHolder;
import com.fiberhome.utils.Param;

/**
 * Description:启一个线程用于监听从kafka消费消息
 * 
 * @author sjZhang
 * @date 2017年12月15日上午10:36:22
 */
@Component
public class ReceiveMessageService implements Runnable {

	private Logger logger = LoggerFactory.getLogger(ReceiveMessageService.class);

	@Override
	public void run() {
		Properties properties = new Properties();
		properties.put("zk.connect", "127.0.0.1:2181");
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("group.id", "gp_list");
		properties.put("enable.auto.commit", "true");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("session.timeout.ms", "30000");
		properties.put("auto.offset.reset", "earliest");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, Object> consumer = new KafkaConsumer(properties);
		consumer.subscribe(Collections.singletonList("cus_receiveResult"));
		
		ConsumerRecords<String, Object> records=null;
		while (true) {
			
			if(Param.openKey){
				if(Param.isSend){
					
					try {
						Thread.currentThread().sleep(5000);
						Map<TopicPartition, List<ConsumerRecord<String, Object>>> recordsMap=new HashMap<>();
						List<ConsumerRecord<String, Object>> recordsList = new ArrayList();
						TopicPartition topicPartition = new TopicPartition("cus_receiveResult", 1);
						recordsMap.put(topicPartition, recordsList);
						records = new ConsumerRecords<>(recordsMap);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}else{
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}else{
				records = consumer.poll(2000);
			}
			
			
			
			
			
			for (ConsumerRecord<String, Object> record : records) {
				String uniqueId = record.key();
				System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
				try {
					Lock locker = LockHolder.getLock(uniqueId);
					if (locker == null) {
						continue;
					}

					locker.setMessage(record.value());
					locker.setNotified(true);
					synchronized (locker) {
						locker.notify();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

	}

}
