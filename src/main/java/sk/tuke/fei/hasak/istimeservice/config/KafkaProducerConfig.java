/*
 * Copyright (c) 2020 Šimon Hašák.
 * All rights reserved.
 */

package sk.tuke.fei.hasak.istimeservice.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import sk.tuke.fei.hasak.istimeservice.kafka.IsTime;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Kafka producer config.
 *
 * @author Šimon Hašák
 */
@Configuration
public class KafkaProducerConfig {

    /**
     * The addresses for kafka producer.
     */
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    /**
     * Schedulled message producer factory.
     *
     * @return the producer factory
     */
    @Bean
    public ProducerFactory<String, IsTime> schedulledMessageProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * Saved event message kafka template.
     *
     * @return the kafka template
     */
    @Bean
    public KafkaTemplate<String, IsTime> savedEventMessageKafkaTemplate() {
        return new KafkaTemplate<>(schedulledMessageProducerFactory());
    }

}
