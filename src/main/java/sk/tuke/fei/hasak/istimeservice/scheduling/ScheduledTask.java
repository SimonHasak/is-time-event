package sk.tuke.fei.hasak.istimeservice.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sk.tuke.fei.hasak.istimeservice.kafka.ReachedTimeMessage;
import sk.tuke.fei.hasak.istimeservice.kafka.ReachedTimeProducer;
import sk.tuke.fei.hasak.istimeservice.service.IsTimeService;

import java.time.LocalDateTime;
import java.util.stream.StreamSupport;

/**
 * @author Šimon Hašák
 */
@Slf4j
@Component
public class ScheduledTask {

    private final ReachedTimeProducer producer;

    private final IsTimeService isTimeService;

    @Autowired
    public ScheduledTask(ReachedTimeProducer producer, IsTimeService isTimeService) {
        this.producer = producer;
        this.isTimeService = isTimeService;
    }

    @Scheduled(fixedRate = 60_000)
    public void checkEventTime() {
//        StreamSupport.stream(isTimeService.findAll().spliterator(), false)
        isTimeService.findAll().forEach(event -> {
            if (event.getTime().isAfter(LocalDateTime.now())) {
                this.producer.sendReachTimeMessage(new ReachedTimeMessage(event.getId()));
                this.isTimeService.deleteById(event.getId());
            }
        });
        log.info("vypis");
    }

}
