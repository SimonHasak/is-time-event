/*
 * Copyright (c) 2020 Šimon Hašák.
 * All rights reserved.
 */

package sk.tuke.fei.hasak.istimeservice.kafka;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * The type Saved event message.
 *
 * @author Šimon Hašák
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SavedEventMessage {

    private long messageId;

    private String email;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime messageTime;


}
