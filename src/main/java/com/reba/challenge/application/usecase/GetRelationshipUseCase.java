package com.reba.challenge.application.usecase;

import com.reba.challenge.application.port.in.GetRelationshipCommand;
import com.reba.challenge.application.port.out.PersonRepository;
import com.reba.challenge.domain.Person;
import com.reba.challenge.domain.RelationshipType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class GetRelationshipUseCase implements GetRelationshipCommand {
    private final PersonRepository personRepository;

    @Override
    public String execute(Long id1, Long id2) {
        validateId(id1, id2);
        Person person1 = personRepository.get(id1);
        Person person2 = personRepository.get(id2);

        return getRelationship(person1, person2);
    }

    private Boolean isBrother(Person person1, Person person2) {
        if (isInvalidFather(person1) || isInvalidFather(person2) || person1.getId().equals(person2.getId()))
            return false;
        return person1.getIdFather().equals(person2.getIdFather());
    }

    private Boolean isUncle(Person person1, Person person2) {
        if (isInvalidFather(person2))
            return false;
        Person father = personRepository.get(person2.getIdFather());
        return isBrother(person1, father);
    }

    private Boolean isCousin(Person person1, Person person2) {
        if (isInvalidFather(person1) || isInvalidFather(person2))
            return false;
        Person father1 = personRepository.get(person1.getIdFather());
        Person father2 = personRepository.get(person2.getIdFather());
        return isBrother(father1, father2);
    }

    private void validateId(Long id1, Long id2) {
        if (id1.equals(id2)) {
            throw new IllegalArgumentException("La persona es la misma.");
        }
    }

    private Boolean isInvalidFather(Person person) {
        return Objects.isNull(person.getIdFather());
    }

    private String getRelationship(Person person1, Person person2) {

        if (isBrother(person1, person2)) {
            return RelationshipType.BROTHER.getDescription();
        }

        if (isUncle(person1, person2)) {
            return RelationshipType.UNCLE.getDescription();
        }

        if (isCousin(person1, person2)) {
            return RelationshipType.COUSIN.getDescription();
        }

        return RelationshipType.NONE.getDescription();
    }


}
