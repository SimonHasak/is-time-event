/*
 * Copyright (c) 2020 Šimon Hašák.
 * All rights reserved.
 */

package sk.tuke.fei.hasak.istimeservice.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import sk.tuke.fei.hasak.istimeservice.kafka.SavedEventMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Kafka consumer config.
 *
 * @author Šimon Hašák
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    /**
     * The addresses available for kafka consumers.
     */
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    /**
     * The group id name for savedEventMessageTopic .
     */
    @Value(value = "${kafka.groupId.saved.event.message}")
    private String groupId;

    /**
     * Saved event message consumer factory.
     *
     * @return the consumer factory
     */
    @Bean
    public ConsumerFactory<String, SavedEventMessage> savedEventMessageConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                new JsonDeserializer<>(SavedEventMessage.class, false));
    }

    /**
     * Kafka listener container factory.
     *
     * @return the concurrent kafka listener container factory
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SavedEventMessage> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, SavedEventMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(savedEventMessageConsumerFactory());
        return factory;
    }

}
