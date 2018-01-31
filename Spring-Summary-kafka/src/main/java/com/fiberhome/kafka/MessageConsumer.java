package com.fiberhome.kafka;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class MessageConsumer {
	private static Properties properties;

	static {
		properties = new Properties();
		properties.put("zk.connect", "127.0.0.1:2181");
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("group.id", "gp_list");
		properties.put("enable.auto.commit", "true");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("session.timeout.ms", "30000");
		properties.put("auto.offset.reset", "earliest");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	}

	/**
	 * 消费消息
	 * @param topic
	 * @throws Exception
	 */
	public void getMsg(String topic) throws Exception {
		KafkaConsumer<String, String> consumer = new KafkaConsumer(properties);
		consumer.subscribe(Collections.singletonList(topic));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(2000);
			for (ConsumerRecord<String, String> record : records) {
				// print the offset,key and value for the consumer records.
				System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
			}
		}

	}

}
