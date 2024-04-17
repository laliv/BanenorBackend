package no.liven.banenor.backend.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

@Service
public class Consumer implements MessageValidator, TrainMessage{

    private Train train;

    private String key;

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(id = "myConsumer", topics = "train", groupId = "springboot-group-1", autoStartup = "false")
    public void listen(String value,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_KEY) String key)
    {
        logger.info(String.format("\n\n Consumed event from topic %s: key = %-10s value = %s \n\n", topic, key, value));
        receiveAndValidateTrainMessage(value);
    }

    @Override
    public Train getTrainMessage() {
        return train;
    }

    public void receiveAndValidateTrainMessage(String value) {
        if(!validateTrainMessage(value)) {
            logger.error(String.format("\n\n We were unamble to validate Train message with key %s \n\n", key));
        }
    }

    @Override
    public boolean validateTrainMessage(String value) {
        try {
            if (Train.DeSerialize(value) instanceof Train) {
                train = Train.DeSerialize(value);
                return true;
            }
        } catch(Exception e) {
            e.getMessage();
        }
        return false;
    }
}
