/*
 * Copyright (c) 2020 Šimon Hašák.
 * All rights reserved.
 */

package sk.tuke.fei.hasak.istimeservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.tuke.fei.hasak.istimeservice.model.TimeEvent;
import sk.tuke.fei.hasak.istimeservice.repository.IsTimeRepository;

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
    public Iterable<TimeEvent> findAll() { return timeRepository.findAll(); }

    public TimeEvent save(TimeEvent event) {
        TimeEvent savedEvent = timeRepository.save(event);
        log.info("[Is-Time-Service] Time event with id {} was saved", savedEvent.getId());
        return savedEvent;
    }

    public void deleteById(long id) {
        log.info("[Is-Time-Service] Time event with id {} was deleted.", id);
        timeRepository.deleteById(id);
    }
}
