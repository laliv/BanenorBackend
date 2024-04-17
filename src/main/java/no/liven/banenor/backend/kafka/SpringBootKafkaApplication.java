package no.liven.banenor.backend.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootKafkaApplication {

    private final Producer producer;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootKafkaApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

   /* @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            this.producer.sendMessage("key", "value");
            MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry.getListenerContainer("myConsumer");
            listenerContainer.start();
        };
    }*/

    @Autowired
    SpringBootKafkaApplication(Producer producer) {
        this.producer = producer;
    }

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

}