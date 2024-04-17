package no.liven.banenor.backend.kafka;

import org.springframework.stereotype.Service;

@Service
public class TrainProducer extends Producer{

    public String produceJson(Train train){
        return Train.Serialize(train);
    }

    public void sendTrainMessage(String key, Train train) {
        sendMessage(key,produceJson(train), Producer.TRAIN_TOPIC);
    }
}
