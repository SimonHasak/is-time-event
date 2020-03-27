/*
 * Copyright (c) 2019 Šimon Hašák.
 * All rights reserved.
 */

package sk.tuke.fei.hasak.istimeservice.exception;

/**
 * The type Event not found exception.
 *
 * @author Šimon Hašák
 */
public class SchedulledEventNotFoundException extends Exception {

    /**
     * Instantiates a new Event not found exception.
     */
    public SchedulledEventNotFoundException() {}

    /**
     * Instantiates a new Event not found exception.
     *
     * @param message the message
     */
    public SchedulledEventNotFoundException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Event not found exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public SchedulledEventNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
