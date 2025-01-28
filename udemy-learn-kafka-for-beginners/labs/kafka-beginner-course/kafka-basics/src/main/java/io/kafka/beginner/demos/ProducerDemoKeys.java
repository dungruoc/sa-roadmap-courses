package io.kafka.beginner.demos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import java.lang.Exception;
import java.lang.Thread;
import java.lang.InterruptedException;

public class ProducerDemoKeys {

    private static final Logger logger = LoggerFactory.getLogger(ProducerDemoKeys.class.getSimpleName());

    public static void main(String[] args) {
        logger.info("Start");


        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        String topic = "second_topic";
        properties.setProperty("topic", topic);
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        properties.setProperty("batch.size", "400");

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for (int keyId = 0; keyId < 10; keyId++) {
            int round_size = 10;
            for (int i = 0; i < round_size; i++) {
                String key = "id_" + keyId;
                int msgId = keyId * round_size + i;
                String value = "hello world ProducerDemoKeys " + msgId;

                ProducerRecord<String, String> record = new ProducerRecord<>(
                    topic, key, value 
                );

                producer.send(record, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        if (exception == null) {
                            logger.info("Received metadata for " + msgId + ":\n" +
                                "Topic: " + metadata.topic() + "\n" +
                                "Key: " + key + "\n" +
                                "Partition: " + metadata.partition() + "\n" +
                                "Offset: " + metadata.offset() + "\n" +
                                "Timestamp: " + metadata.timestamp()
                            );
                        } else {
                            logger.error("Exception caught: ", exception);                    
                        }
                    }
                });
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        producer.flush();
        producer.close();

    }
}