/*
 * Copyright (c) 2020 Šimon Hašák.
 * All rights reserved.
 */

package sk.tuke.fei.hasak.istimeservice.controller;

import lombok.SneakyThrows;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import sk.tuke.fei.hasak.istimeservice.model.SchedulledEvent;
import sk.tuke.fei.hasak.istimeservice.service.IsTimeService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * The type Is time controller.
 *
 * @author Šimon Hašák
 */
@RestController
@RequestMapping("/is-time")
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
    public CollectionModel<EntityModel<SchedulledEvent>> findAll() {
        List<EntityModel<SchedulledEvent>> events = StreamSupport.stream(timeService.findAll().spliterator(), false)
                .map(event -> new EntityModel<>(event, linkTo(IsTimeController.class).withSelfRel()))
                .collect(Collectors.toList());

        return new CollectionModel<>(events);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public EntityModel<SchedulledEvent> findById(@PathVariable long id) {

        SchedulledEvent event = timeService.findById(id);

        event.add(linkTo(methodOn(IsTimeController.class).findById(event.getSchedulledId())).withSelfRel(),
                linkTo(IsTimeController.class).withSelfRel());

        return new EntityModel<>(event);
    }

    @SneakyThrows
    @GetMapping("/message/{id}")
    public EntityModel<SchedulledEvent> findByMessageId(@PathVariable long id) {

        SchedulledEvent event = timeService.findByMessageId(id);

        event.add(linkTo(methodOn(IsTimeController.class).findById(event.getMessageId())).withSelfRel(),
                linkTo(IsTimeController.class).withSelfRel());

        return new EntityModel<>(event);
    }

    @PostMapping()
    public EntityModel<SchedulledEvent> save(@RequestBody SchedulledEvent event) {
        SchedulledEvent savedEvent = timeService.save(event);

        savedEvent.add(linkTo(methodOn(IsTimeController.class).findById(event.getSchedulledId())).withSelfRel(),
                linkTo(IsTimeController.class).withSelfRel());

        return new EntityModel<>(savedEvent);
    }

    /**
     * Update event.
     *
     * @param event the new event
     * @param id    the id of old event to be updated by new one.
     */
    @SneakyThrows
    @PutMapping("/{id}")
    public EntityModel<SchedulledEvent> update(@RequestBody SchedulledEvent event, @PathVariable long id) {
        SchedulledEvent updatedEvent = timeService.update(event, id);

        updatedEvent.add(linkTo(methodOn(IsTimeController.class).findById(updatedEvent.getSchedulledId())).withSelfRel(),
                linkTo(IsTimeController.class).withSelfRel());

        return new EntityModel<>(updatedEvent);
    }

    /**
     * Delete event by its id.
     *
     * @param id the id.
     */
    @SneakyThrows
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) { timeService.deleteById(id); }

}
