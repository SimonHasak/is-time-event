package sk.tuke.fei.hasak.istimeservice.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sk.tuke.fei.hasak.istimeservice.kafka.SchedulledMessage;
import sk.tuke.fei.hasak.istimeservice.kafka.SchedulledMessageProducer;
import sk.tuke.fei.hasak.istimeservice.service.IsTimeService;

import java.time.LocalDateTime;

/**
 * @author Šimon Hašák
 */
@Slf4j
@Component
public class ScheduledTask {

    private final SchedulledMessageProducer producer;

    private final IsTimeService isTimeService;

    @Autowired
    public ScheduledTask(SchedulledMessageProducer producer, IsTimeService isTimeService) {
        this.producer = producer;
        this.isTimeService = isTimeService;
    }

    @Scheduled(fixedRate = 60_000)
    public void checkEventTime() {
        isTimeService.findAll().forEach(event -> {
            if (event.getSchedulledTime().isAfter(LocalDateTime.now())) {
                this.producer.sendReachTimeMessage(new SchedulledMessage(event.getMessageId()));
                this.isTimeService.deleteById(event.getSchedulledId());
            }
        });
        log.info("vypis");
    }

}
