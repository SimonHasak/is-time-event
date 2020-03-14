package sk.tuke.fei.hasak.istimeservice.kafka;

import org.springframework.kafka.support.serializer.JsonDeserializer;

public class SavedEventMessageDeserializer extends JsonDeserializer<SavedEventMessage> {

    public SavedEventMessageDeserializer() { super(SavedEventMessage.class); }
}
