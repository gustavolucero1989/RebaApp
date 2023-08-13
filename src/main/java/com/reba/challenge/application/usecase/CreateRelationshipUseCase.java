package com.reba.challenge.application.usecase;

import com.reba.challenge.application.port.in.CreateRelationshipCommand;
import com.reba.challenge.application.port.out.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateRelationshipUseCase implements CreateRelationshipCommand {
    private final PersonRepository personRepository;

    @Override
    public String execute(Long id1, Long id2) {

        return personRepository.createRelationship(id1, id2);
    }
}
