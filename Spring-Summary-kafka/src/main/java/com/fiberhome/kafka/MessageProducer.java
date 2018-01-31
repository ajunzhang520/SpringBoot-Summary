package com.fiberhome.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Description:发送消息
 * 
 * @author sjZhang
 * @date 2017年12月14日下午4:29:39
 */
public class MessageProducer {
	private static Properties properties;

	static {
		properties = new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("acks", "1");
		properties.put("retries", 0);
		properties.put("batch.size", 16384);
		properties.put("linger.ms", 0);
		properties.put("buffer.memory", 33554432);
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

	}

	/**
	 * 发送消息
	 *
	 */
	public void sendMsg(String topic, String key, String value) {
		// 实例化produce
		KafkaProducer<String, String> kp = new KafkaProducer<String, String>(properties);
		// 消息封装
		ProducerRecord<String, String> pr = new ProducerRecord<String, String>(topic, key, value);
		// 发送数据
	    kp.send(pr);
//		kp.send(pr, new Callback() { // 回调函数
//			@Override
//			public void onCompletion(RecordMetadata metadata, Exception exception) {
//				if (null != exception) {
//					System.out.println("记录的offset在:" + metadata.offset());
//					System.out.println(exception.getMessage() + exception);
//				}
//			}
//		});
		// 关闭produce
		kp.close();
	}

}
