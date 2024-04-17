package no.liven.banenor.backend.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Getter
@Setter
@Component
public class Train {

    @Value("${banenor.train.gps}")
    private double gpsLocation;

    @Value("${banenor.train.speed}")
    private double speed;

    @Autowired
    private EngineDiagnostics engineDiagnostics;

    @Autowired
    private Infrastructure currentInfrastructure;

   public Train(@Value("${banenor.train.gps}")
                @JsonProperty("gpsLocation")
                double gpsLocation,
                @Value("${banenor.train.speed}")
                @JsonProperty("speed")
                double speed,
                @JsonProperty("engineDiagnostics")
                EngineDiagnostics engineDiagnostics,
                @JsonProperty("engineDiagnostics")
                Infrastructure currentInfrastructure) {
        this.gpsLocation = gpsLocation;
        this.speed = speed;
        this.engineDiagnostics = engineDiagnostics;
        this.currentInfrastructure = currentInfrastructure;
    }

    public static String Serialize(Train p) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(p);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{ \"error\": \"Failed to serialize train-object\"}";
        }
    }

    public static Train DeSerialize(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, Train.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Train{" +
                "gpsLocation=" + gpsLocation +
                ", speed=" + speed +
                ", engineDiagnostics=" + engineDiagnostics +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Train train)) return false;
        return Double.compare(gpsLocation, train.gpsLocation) == 0 && Double.compare(speed, train.speed) == 0 && Objects.equals(engineDiagnostics, train.engineDiagnostics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gpsLocation, speed, engineDiagnostics);
    }
}