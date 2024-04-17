package no.liven.banenor.backend.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.KafkaHeaders;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
@SpringBootTest
public class InfrastructureMessageTest {

    @Autowired
    Infrastructure infrastructure;

    @Autowired
    InfrastructureProducer producer;

    @Autowired
    Consumer consumer;


    @Test
    public void infrastructureTest(){
        String infrastructureUUID = UUID.randomUUID().toString();
        producer.sendInfrastructureMessage(infrastructureUUID,infrastructure);

        MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry.getListenerContainer("myConsumer");
        listenerContainer.start();

        consumer.listen(Infrastructure.Serialize(infrastructure), KafkaHeaders.RECEIVED_TOPIC,infrastructureUUID);

        Train receivedTrain = consumer.getTrainMessage();

        assertTrue(receivedTrain instanceof Train);
        assertTrue(receivedTrain.equals(infrastructure));
        assertTrue(receivedTrain.getCurrentInfrastructure().getStart().equals("Oslo"));
        assertTrue(receivedTrain.getCurrentInfrastructure().getEnd().equals("Trondheim"));

    }

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

}
