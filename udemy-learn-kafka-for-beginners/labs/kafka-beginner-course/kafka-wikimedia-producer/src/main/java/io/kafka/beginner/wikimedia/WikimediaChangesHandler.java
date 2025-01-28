package io.kafka.beginner.wikimedia;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WikimediaChangesHandler implements EventHandler {

    KafkaProducer<String, String> kafkaProducer;
    String topic;
    private final Logger logger = LoggerFactory.getLogger(WikimediaChangesHandler.class.getSimpleName());

    public WikimediaChangesHandler(KafkaProducer<String, String> kafkaProducer, String topic) {
        this.kafkaProducer = kafkaProducer;
        this.topic = topic;
    }

    @Override
    public void onOpen() throws Exception {
        logger.info("WikimediaChangesHandler onOpen");
    }

    @Override
    public void onClosed() throws Exception {
        logger.info("WikimediaChangesHandler onClose");
        kafkaProducer.close();
    }

    @Override
    public void onMessage(String event, MessageEvent messageEvent) throws Exception {
        logger.info("WikimediaChangesHandler onMessage - sending\n" + messageEvent.getData());
        kafkaProducer.send(
            new ProducerRecord<String, String>(
                topic, messageEvent.getData()
            )
        );
    }


    @Override
    public void onComment(String comment) throws Exception {
        logger.info("WikimediaChangesHandler onComment");
    }

    @Override
    public void onError(Throwable t) {
        logger.error("WikimediaChangesHandler onError", t);
    }

}