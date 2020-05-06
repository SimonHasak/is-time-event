/*
 * Copyright (c) 2020 Šimon Hašák.
 * All rights reserved.
 */

package sk.tuke.fei.hasak.istimeservice.kafka;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import sk.tuke.fei.hasak.istimeservice.model.SchedulledEvent;
import sk.tuke.fei.hasak.istimeservice.service.IsTimeService;

/**
 * The type Saved event message kafka listener.
 *
 * @author Šimon Hašák
 */
@Slf4j
@Component
public class MessageDeletedKafkaListener {

    private final IsTimeService isTimeService;

    /**
     * Instantiates a new Saved event message kafka listener.
     *
     * @param isTimeService the is time service
     */
    @Autowired
    public MessageDeletedKafkaListener(IsTimeService isTimeService) {
        this.isTimeService = isTimeService;
    }

    /**
     * Process deleted message and save it into schedulled event.
     *
     * @param message the message
     */
    @SneakyThrows
    @KafkaListener(topics = "${kafka.topic.message.deleted}", groupId = "${kafka.groupId.message.deleted}",
                    containerFactory = "kafkaListenerContainerFactory")
    public void processMessageDeleted(@NonNull MessageDeleted message) {
        SchedulledEvent schedulledEvent = isTimeService.findByMessageId(message.getMessageId());
        log.info("[Is-Time-Service] received:{}", message);
        isTimeService.deleteById(schedulledEvent.getSchedulledId());
    }

}
