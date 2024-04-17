package no.liven.banenor.backend.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
public class Maintenance {

    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private String maintenanceType;

    public Maintenance(@JsonProperty("startDate") ZonedDateTime startDate, @JsonProperty("endDate")ZonedDateTime endDate, @JsonProperty("maintenanceType")String maintenanceType) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.maintenanceType = maintenanceType;
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", maintenanceType='" + maintenanceType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Maintenance that)) return false;
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(maintenanceType, that.maintenanceType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, maintenanceType);
    }
}
