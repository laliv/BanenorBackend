package no.liven.banenor.backend.kafka;

import no.liven.banenor.backend.train.Train;
import no.liven.banenor.backend.train.TrainProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.KafkaHeaders;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
@SpringBootTest
public class TrainMessageTest {

    @Autowired
    Train train;

    @Autowired
    TrainProducer producer;

    @Autowired
    Consumer consumer;

    @Test
    public void produceTrainMessageTest(){
        String trainUUID = UUID.randomUUID().toString();
        producer.sendTrainMessage(trainUUID,train);

        MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry.getListenerContainer("myConsumer");
        listenerContainer.start();

        consumer.listen(Train.Serialize(train), KafkaHeaders.RECEIVED_TOPIC,trainUUID);

        Train receivedTrain = consumer.getTrainMessage();

        assertTrue(receivedTrain instanceof Train);
        assertTrue(receivedTrain.equals(train));

    }


    @Test
    public void trainOnInfrastructureTest(){
        String trainUUID = UUID.randomUUID().toString();
        producer.sendTrainMessage(trainUUID,train);

        MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry.getListenerContainer("myConsumer");
        listenerContainer.start();

        consumer.listen(Train.Serialize(train), KafkaHeaders.RECEIVED_TOPIC,trainUUID);

        Train receivedTrain = consumer.getTrainMessage();

        assertTrue(receivedTrain instanceof Train);
        assertTrue(receivedTrain.equals(train));
        assertTrue(receivedTrain.getCurrentInfrastructure().getStart().equals("Oslo"));

    }

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

}
