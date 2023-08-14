package com.reba.challenge.adapter.controller;

import com.reba.challenge.application.port.in.CreateRelationshipCommand;
import com.reba.challenge.application.port.in.GetRelationshipCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge/relationships")
public class RelationshipControllerAdapter {
    private final CreateRelationshipCommand createRelationshipCommand;
    private final GetRelationshipCommand getRelationshipCommand;

    @PostMapping("/{id1}/father/{id2}")
    public String create(@PathVariable Long id1, @PathVariable Long id2) {
        log.debug("Entrando al endpoint: create | uri: /api/v1/challenge/relationships/{id}/father/{id2} | id1: {} | id2: {}", id1, id2);
        return createRelationshipCommand.execute(id1, id2);
    }

    @GetMapping("/{id1}/{id2}")
    public String get(@PathVariable Long id1, @PathVariable Long id2) {
        log.debug("Entrando al endpoint: get | uri: /api/v1/challenge/relationships/{id}/{id2} | id1: {} | id2: {}", id1, id2);
        return getRelationshipCommand.execute(id1, id2);
    }
}
