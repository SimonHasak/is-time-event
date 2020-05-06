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
 * The type Is Time message.
 *
 * @author Šimon Hašák
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IsTime {

    private long messageId;

}
