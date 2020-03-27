/*
 * Copyright (c) 2020 Šimon Hašák.
 * All rights reserved.
 */

package sk.tuke.fei.hasak.istimeservice.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.tuke.fei.hasak.istimeservice.exception.SchedulledEventNotFoundException;
import sk.tuke.fei.hasak.istimeservice.model.SchedulledEvent;
import sk.tuke.fei.hasak.istimeservice.repository.IsTimeRepository;

import java.util.Optional;

/**
 * The type Is time service.
 *
 * @author Šimon Hašák
 */
@Slf4j
@Service
public class IsTimeService {

    private final IsTimeRepository timeRepository;

    /**
     * Instantiates a new Is time service.
     *
     * @param timeRepository the time repository
     */
    public IsTimeService(IsTimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    /**
     * Find all iterable.
     *
     * @return the iterable
     */
    public Iterable<SchedulledEvent> findAll() { return timeRepository.findAll(); }

    public SchedulledEvent findById(long id) throws SchedulledEventNotFoundException {
        Optional<SchedulledEvent> eventOptional = timeRepository.findById(id);

        if (eventOptional.isEmpty()) {
            throw new SchedulledEventNotFoundException("Event with id: " + id + " was not found.");
        }

        return eventOptional.get();
    }

    public SchedulledEvent findByMessageId(long id) throws SchedulledEventNotFoundException {
        Optional<SchedulledEvent> schedulledEventOptional = timeRepository.findByMessageId(id);

        if (schedulledEventOptional.isEmpty()) {
            throw new SchedulledEventNotFoundException("Event with messageId: " + id + " was not found.");
        }

        return schedulledEventOptional.get();
    }

    public SchedulledEvent save(@NonNull SchedulledEvent event) {
        SchedulledEvent savedEvent = timeRepository.save(event);
        log.info("[Is-Time-Service] Time event with id {} was saved", savedEvent.getSchedulledId());
        return savedEvent;
    }

    public SchedulledEvent update(@NonNull SchedulledEvent event, long id) throws SchedulledEventNotFoundException {
        Optional<SchedulledEvent> eventOptional = timeRepository.findById(id);

        if (eventOptional.isEmpty()) {
            throw new SchedulledEventNotFoundException("Event with id: " + id + " was not found.");
        }

        log.info("Is-time-service] Event with id: {} updated", id);

        event.setSchedulledId(id);
        timeRepository.save(event);

        return event;
    }

    public void deleteById(long id) {
        log.info("[Is-Time-Service] Time event with id {} was deleted.", id);
        timeRepository.deleteById(id);
    }
}
