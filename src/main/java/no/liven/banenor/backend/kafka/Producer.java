package no.liven.banenor.backend.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    protected static final String TRAIN_TOPIC = "train";
    protected static final String INTERFACE_TOPIC = "infrastructure";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String key, String value, String topic) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, value);
        future.whenComplete((result, ex) -> {
                    if (ex == null) {
                        logger.info(String.format("\n\n Produced event to topic %s: key = %-10s value = %s \n\n", topic, key, value));
                    } else {
                        ex.printStackTrace();
                    }
                });

    }
}