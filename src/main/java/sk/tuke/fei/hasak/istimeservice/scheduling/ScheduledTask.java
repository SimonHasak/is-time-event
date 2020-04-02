/*
 * Copyright (c) 2020 Šimon Hašák.
 * All rights reserved.
 */

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
 * The type Scheduled task.
 *
 * @author Šimon Hašák
 */
@Slf4j
@Component
public class ScheduledTask {

    private final SchedulledMessageProducer producer;

    private final IsTimeService isTimeService;

    /**
     * Instantiates a new Scheduled task.
     *
     * @param producer      the producer
     * @param isTimeService the is time service
     */
    @Autowired
    public ScheduledTask(SchedulledMessageProducer producer, IsTimeService isTimeService) {
        this.producer = producer;
        this.isTimeService = isTimeService;
    }

    /**
     * Check event if its time has passed.
     */
    @Scheduled(fixedRate = 60_000)
    public void checkEventTime() {
        isTimeService.findAll().forEach(event -> {
            if (event.getSchedulledTime().isBefore(LocalDateTime.now())) {
                this.producer.sendReachTimeMessage(new SchedulledMessage(event.getMessageId()));
                this.isTimeService.deleteById(event.getSchedulledId());
            }
        });
        log.info("Schedulled time print.");
    }

}
