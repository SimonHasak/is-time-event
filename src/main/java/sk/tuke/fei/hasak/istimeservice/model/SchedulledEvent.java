/*
 * Copyright (c) 2020 Šimon Hašák.
 * All rights reserved.
 */

package sk.tuke.fei.hasak.istimeservice.model;

import lombok.*;
import org.apache.tomcat.jni.Local;
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
public class TimeEvent extends RepresentationModel<TimeEvent> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    public TimeEvent(LocalDateTime time) {
        this.time = time;
    }

}
