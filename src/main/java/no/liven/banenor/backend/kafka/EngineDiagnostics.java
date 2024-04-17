package no.liven.banenor.backend.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

@Getter
@Setter
public class EngineDiagnostics {

    @Value("${banenor.train.engine.model}")
    private String model;
    @Value("${banenor.train.engine.type}")
    private String type;
    @Value("${banenor.train.engine.power}")
    private String power;


    @Override
    public String toString() {
        return "EngineDiagnostics{" +
                "model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", power='" + power + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EngineDiagnostics that)) return false;
        return Objects.equals(model, that.model) && Objects.equals(type, that.type) && Objects.equals(power, that.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, type, power);
    }
}
