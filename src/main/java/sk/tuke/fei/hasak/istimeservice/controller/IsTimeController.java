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
     * Find all schedulled events wrapped in collection model.
     *
     * @return the collection model
     */
    @GetMapping()
    public CollectionModel<EntityModel<SchedulledEvent>> findAll() {
        List<EntityModel<SchedulledEvent>> events = StreamSupport.stream(timeService.findAll().spliterator(), false)
                .map(event -> new EntityModel<>(event, linkTo(IsTimeController.class).withSelfRel()))
                .collect(Collectors.toList());

        return new CollectionModel<>(events);
    }

    /**
     * Find by id schedulled event wrapped in entity model.
     *
     * @param id the id of schedduled event
     * @return the scheddulled event wrapped in entity model
     */
    @SneakyThrows
    @GetMapping("/{id}")
    public EntityModel<SchedulledEvent> findById(@PathVariable long id) {

        SchedulledEvent event = timeService.findById(id);

        event.add(linkTo(methodOn(IsTimeController.class).findById(event.getSchedulledId())).withSelfRel(),
                linkTo(IsTimeController.class).withSelfRel());

        return new EntityModel<>(event);
    }

    /**
     * Find saved event by message id wrapped in entity model.
     *
     * @param id the message id
     * @return the saved event wrapped in entity model
     */
    @SneakyThrows
    @GetMapping("/message/{id}")
    public EntityModel<SchedulledEvent> findByMessageId(@PathVariable long id) {

        SchedulledEvent event = timeService.findByMessageId(id);

        event.add(linkTo(methodOn(IsTimeController.class).findById(event.getMessageId())).withSelfRel(),
                linkTo(IsTimeController.class).withSelfRel());

        return new EntityModel<>(event);
    }

    /**
     * Save scchedulled event and wrapped it in entity model.
     *
     * @param event the event to be saved
     * @return the saved event wrapped in entity model
     */
    @PostMapping()
    public EntityModel<SchedulledEvent> save(@RequestBody SchedulledEvent event) {
        SchedulledEvent savedEvent = timeService.save(event);

        savedEvent.add(linkTo(methodOn(IsTimeController.class).findById(event.getSchedulledId())).withSelfRel(),
                linkTo(IsTimeController.class).withSelfRel());

        return new EntityModel<>(savedEvent);
    }


    /**
     * Update schedulled event wrapped in entity model.
     *
     * @param event the schedulled event to update old event
     * @param id    the id of schedulled event to be updated
     * @return the updated schedulled event wrapped in entity model
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
     * Delete schedulled event.
     *
     * @param id the id of schedulled event
     */
    @SneakyThrows
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) { timeService.deleteById(id); }

}
