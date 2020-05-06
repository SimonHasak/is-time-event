/*
 * Copyright (c) 2020 Šimon Hašák.
 * All rights reserved.
 */

package sk.tuke.fei.hasak.istimeservice.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The type Saved event message.
 *
 * @author Šimon Hašák
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageDeleted {

    private long messageId;

}
