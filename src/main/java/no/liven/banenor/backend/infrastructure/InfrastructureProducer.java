package no.liven.banenor.backend.infrastructure;

import no.liven.banenor.backend.kafka.Producer;
import org.springframework.stereotype.Service;

@Service
public class InfrastructureProducer extends Producer {

        public String produceJson(Infrastructure infra){
            return Infrastructure.Serialize(infra);
        }

        public void sendInfrastructureMessage(String key, Infrastructure infra) {
            sendMessage(key,produceJson(infra), Producer.INTERFACE_TOPIC);
        }
}
