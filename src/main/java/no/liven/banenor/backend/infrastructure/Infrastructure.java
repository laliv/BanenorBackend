package no.liven.banenor.backend.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import no.liven.banenor.backend.train.Train;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;

@Getter
public class Infrastructure {

    @Value("#{${banenor.infrastructure.start}}")
    private String start;

    @Value("#{${banenor.infrastructure.end}}")
    private String end;

    @Value("#{${banenor.infrastructure.stations}}")
    private ArrayList<String> stations;

    private ArrayList<Maintenance> maintenanceList;

    //private Incident incident;

    private ArrayList<Train> trains;

    public Infrastructure(@JsonProperty("start") String start,
                          @JsonProperty("start") String end,
                          @JsonProperty("stations") ArrayList<String> stations,
                          @JsonProperty("mainteneance") ArrayList<Maintenance> mainteneance,
                          @JsonProperty("incident") String incident,
                          @JsonProperty("trains") ArrayList<Train> trains) {
        this.start = start;
        this.end = end;
        this.stations = stations;
        this.maintenanceList = mainteneance;
       // this.incident = incident;
        this.trains = trains;
    }

    public static String Serialize(Infrastructure p) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(p);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{ \"error\": \"Failed to serialize object\"}";
        }
    }

    public static Infrastructure DeSerialize(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, Infrastructure.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
