/*
 * Copyright (c) 2020 Šimon Hašák.
 * All rights reserved.
 */

package sk.tuke.fei.hasak.istimeservice.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.tuke.fei.hasak.istimeservice.model.TimeEvent;
import sk.tuke.fei.hasak.istimeservice.service.IsTimeService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * The type Is time controller.
 *
 * @author Šimon Hašák
 */
@RestController
@RequestMapping("/is-time-event")
public class IsTimeController {

    private final IsTimeService timeService;

    /**
     * Instantiates a new Is time controller.
     *
     * @param timeService the time service
     */
    public IsTimeController(IsTimeService timeService) {
        this.timeService = timeService;
    }

    /**
     * Find all TimeEvents collection model.
     *
     * @return the collection model of TimeEvents
     */
    @GetMapping()
    public CollectionModel<EntityModel<TimeEvent>> findAll() {
        List<EntityModel<TimeEvent>> events = StreamSupport.stream(timeService.findAll().spliterator(), false)
                .map(event -> new EntityModel<>(event, linkTo(IsTimeController.class).withSelfRel()))
                .collect(Collectors.toList());

        return new CollectionModel<>(events);
    }

}
