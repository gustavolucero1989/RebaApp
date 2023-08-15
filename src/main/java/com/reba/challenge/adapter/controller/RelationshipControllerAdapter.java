package com.reba.challenge.adapter.controller;

import com.reba.challenge.application.port.in.CreateRelationshipCommand;
import com.reba.challenge.application.port.in.GetRelationshipCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge/relationships")
@Tag(name = "Relationship", description = "Relationship controller")
public class RelationshipControllerAdapter {
    private final CreateRelationshipCommand createRelationshipCommand;
    private final GetRelationshipCommand getRelationshipCommand;

    @Operation(summary = "Create Relationship", description = """
        Validations:
                
        The IDs must exist within the database.
                
        It's a left-to-right relationship where id1 is the father of id2.""")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @PostMapping("/{id1}/father/{id2}")
    public ResponseEntity<String> create(@PathVariable Long id1, @PathVariable Long id2) {
        log.debug("Entrando al endpoint: create | uri: /api/v1/challenge/relationships/{id}/father/{id2} | id1: {} | id2: {}", id1, id2);
        String relationship = createRelationshipCommand.execute(id1, id2);
        return ResponseEntity.status(HttpStatus.CREATED).body(relationship);
    }

    @Operation(summary = "Get Relationship", description = """
        Validations:
                
        id1 must be different from id2.
               
        The value of esPadre cannot be null to validate the relationship.
               
        The possible responses are "HERMAN@", "TI@", "PRIM@", "NO EXISTE RELACION".""")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok", content = @Content),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @GetMapping("/{id1}/{id2}")
    public String get(@PathVariable Long id1, @PathVariable Long id2) {
        log.debug("Entrando al endpoint: get | uri: /api/v1/challenge/relationships/{id}/{id2} | id1: {} | id2: {}", id1, id2);
        return getRelationshipCommand.execute(id1, id2);
    }
}
