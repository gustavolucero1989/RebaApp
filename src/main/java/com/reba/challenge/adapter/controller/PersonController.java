package com.reba.challenge.adapter.controller;

import com.reba.challenge.adapter.controller.model.PersonBody;
import com.reba.challenge.application.port.in.CreatePersonCommand;
import com.reba.challenge.application.port.in.DeletePersonCommand;
import com.reba.challenge.application.port.in.GetPersonQuery;
import com.reba.challenge.application.port.in.UpdatePersonCommand;
import com.reba.challenge.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge")
public class PersonController {
    private final CreatePersonCommand createPersonCommand;
    private final GetPersonQuery getPersonQuery;
    private final UpdatePersonCommand updatePersonCommand;
    private final DeletePersonCommand deletePersonCommand;

    @PostMapping("/persons")
    public Person create(@RequestBody PersonBody body) {
        log.debug("Entrando al endpoint: create | uri: /api/v1/challenge/persons | body: {}", body);
        return createPersonCommand.execute(body.toDomain());
    }

    @GetMapping("/persons/{id}")
    public Person get(@PathVariable Long id) {
        log.debug("Entrando al endpoint: get | uri: /api/v1/challenge/persons/{id} | id: {}", id);
        return getPersonQuery.execute(id);
    }

    @PutMapping("/persons/{id}")
    public Person update(@RequestBody PersonBody body, @PathVariable Long id) {
        log.debug("Entrando al endpoint: update | uri: /api/v1/challenge/persons/{id} | body: {} | id: {}", body, id);
        return updatePersonCommand.execute(body.toDomain(), id);
    }

    @DeleteMapping("/persons/{id}")
    public void delete(@PathVariable Long id) {
        log.debug("Entrando al endpoint: delete | uri: /api/v1/challenge/persons/{id} | id: {}", id);
        deletePersonCommand.execute(id);
    }

}
