package no.liven.banenor.backend.kafka;

import no.liven.banenor.backend.train.EngineDiagnostics;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Value("${banenor.train.gps}")
    private double gpsLocation;

    @Value("${banenor.train.speed}")
    private double speed;


    @Bean
    public double getGpsLocation(){
        return gpsLocation;
    }

    @Bean
    public double getSpeed(){
        return gpsLocation;
    }

    @Bean
    EngineDiagnostics getEngine(){
        return new EngineDiagnostics();
    }


}
