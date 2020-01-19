/*
 * Copyright (c) 2020 Šimon Hašák.
 * All rights reserved.
 */

package sk.tuke.fei.hasak.istimeservice.service;

import org.springframework.stereotype.Service;
import sk.tuke.fei.hasak.istimeservice.model.TimeEvent;
import sk.tuke.fei.hasak.istimeservice.repository.IsTimeRepository;

/**
 * The type Is time service.
 *
 * @author Šimon Hašák
 */
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
}
