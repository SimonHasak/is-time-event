/*
 * Copyright (c) 2020 Šimon Hašák.
 * All rights reserved.
 */

package sk.tuke.fei.hasak.istimeservice.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The type Time event.
 *
 * @author Šimon Hašák
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "is_time_tb")
public class SchedulledEvent extends RepresentationModel<SchedulledEvent> {

    @Id
    @Column(name = "schedulled_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedulledId;

    @Column(name = "message_id", nullable = false)
    private Long messageId;

    @Column(name = "time", nullable = false)
    private LocalDateTime schedulledTime;

    /**
     * Instantiates a new Schedulled event.
     *
     * @param messageId      the message id
     * @param schedulledTime the schedulled time
     */
    public SchedulledEvent(Long messageId, LocalDateTime schedulledTime) {
        this.messageId = messageId;
        this.schedulledTime = schedulledTime;
    }

}
