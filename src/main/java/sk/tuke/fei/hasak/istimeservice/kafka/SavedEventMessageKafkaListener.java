package sk.tuke.fei.hasak.istimeservice.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import sk.tuke.fei.hasak.istimeservice.model.TimeEvent;
import sk.tuke.fei.hasak.istimeservice.service.IsTimeService;

@Slf4j
@Component
public class SavedEventMessageKafkaListener {

    private final IsTimeService isTimeService;

    @Autowired
    public SavedEventMessageKafkaListener(IsTimeService isTimeService) {
        this.isTimeService = isTimeService;
    }

    @KafkaListener(topics = "${kafka.saved.event}", groupId = "${kafka.group.saved.event}")
    public void processSavedEventMessage(SavedEventMessage message) {
        log.info("[Is-Time-Service] received:{}", message);
        isTimeService.save(new TimeEvent(message.getTime()));
    }

}
