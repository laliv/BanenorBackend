package no.liven.banenor.backend.kafka;

public interface MessageValidator {

    public boolean validateTrainMessage(String value);
}
