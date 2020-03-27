package sk.tuke.fei.hasak.istimeservice.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReachedTimeProducer {

    @Value("${kafka.reached.time.event}")
    private String topic;

    private KafkaTemplate<String, ReachedTimeMessage> kafkaTemplate;

    @Autowired
    public ReachedTimeProducer(KafkaTemplate<String, ReachedTimeMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendReachTimeMessage(ReachedTimeMessage message) {
        log.info("[Is-Time-Service] send:{}", message.toString());
        kafkaTemplate.send(topic, message);
    }
}