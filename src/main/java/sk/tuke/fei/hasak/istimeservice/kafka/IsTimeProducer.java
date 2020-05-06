/*
 * Copyright (c) 2020 Šimon Hašák.
 * All rights reserved.
 */

package sk.tuke.fei.hasak.istimeservice.kafka;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * The type Is Time message producer.
 *
 * @author Šimon Hašák
 */
@Slf4j
@Component
public class IsTimeProducer {

    /**
     * The topic for publishing messages.
     */
    @Value("${kafka.topic.schedulled.message}")
    private String topic;

    /**
     * KafkaTemplate for publishing messages to selected topic.
     */
    private KafkaTemplate<String, IsTime> kafkaTemplate;

    /**
     * Instantiates a new Schedulled message producer.
     *
     * @param kafkaTemplate the kafka template
     */
    @Autowired
    public IsTimeProducer(KafkaTemplate<String, IsTime> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Send reach time message to selected topic.
     *
     * @param message the message
     */
    public void sendReachTimeMessage(@NonNull IsTime message) {
        log.info("[Is-Time-Service] send: {} to topic: {}", message.toString(), topic);
        kafkaTemplate.send(topic, message);
    }
}