package sk.tuke.fei.hasak.istimeservice.kafka;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SchedulledMessageProducer {

    @Value("${kafka.topic.schedulled.message}")
    private String topic;

    private KafkaTemplate<String, SchedulledMessage> kafkaTemplate;

    @Autowired
    public SchedulledMessageProducer(KafkaTemplate<String, SchedulledMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendReachTimeMessage(@NonNull SchedulledMessage message) {
        log.info("[Is-Time-Service] send:{}", message.toString());
        kafkaTemplate.send(topic, message);
    }
}