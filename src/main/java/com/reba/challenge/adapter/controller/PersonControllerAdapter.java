package com.reba.challenge.adapter.controller;

import com.reba.challenge.adapter.controller.model.PersonBody;
import com.reba.challenge.application.port.in.CreatePersonCommand;
import com.reba.challenge.application.port.in.DeletePersonCommand;
import com.reba.challenge.application.port.in.GetPersonQuery;
import com.reba.challenge.application.port.in.UpdatePersonCommand;
import com.reba.challenge.domain.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v1/challenge/persons")
@Tag(name = "Person", description = "Person controller")
public class PersonControllerAdapter {
    private final CreatePersonCommand createPersonCommand;
    private final GetPersonQuery getPersonQuery;
    private final UpdatePersonCommand updatePersonCommand;
    private final DeletePersonCommand deletePersonCommand;

    @Operation(summary = "Create Person", description = """
        Validations:
                
        The person must be over 18 years of age.
                
        Persons are identified by
        documentType, documentNumber, country.
                
        Persons must have at least one contact information.
                
        They can have any nationality.""")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Created", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @PostMapping
    public ResponseEntity<Person> create(@RequestBody PersonBody body) {
        log.debug("Entrando al endpoint: create | uri: /api/v1/challenge/persons | body: {}", body);
        Person person = createPersonCommand.execute(body.toDomain());
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @Operation(summary = "Get Person", description = """
        Validations:
                
        The person's ID must be present to retrieve it.""")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @GetMapping("/{id}")
    public Person getById(@PathVariable Long id) {
        log.debug("Entrando al endpoint: get | uri: /api/v1/challenge/persons/{id} | id: {}", id);
        return getPersonQuery.execute(id);
    }

    @Operation(summary = "Update Person", description = """
        Validations:
                
        The person's ID must be present to update it.
                
        The person must be over 18 years of age.
                
        Persons are identified by
        documentType, documentNumber, country.
                
        Persons must have at least one contact information.
                
        They can have any nationality.""")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @PutMapping("/{id}")
    public Person update(@RequestBody PersonBody body, @PathVariable Long id) {
        log.debug("Entrando al endpoint: update | uri: /api/v1/challenge/persons/{id} | body: {} | id: {}", body, id);
        return updatePersonCommand.execute(body.toDomain(), id);
    }

    @Operation(summary = "Delete Person", description = """
        Validations:
                
        The person's ID must be present to retrieve it.""")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("Entrando al endpoint: delete | uri: /api/v1/challenge/persons/{id} | id: {}", id);
        deletePersonCommand.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
