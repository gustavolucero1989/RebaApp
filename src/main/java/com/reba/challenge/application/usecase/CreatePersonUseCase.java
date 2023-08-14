package com.reba.challenge.application.usecase;

import com.reba.challenge.application.port.in.CreatePersonCommand;
import com.reba.challenge.application.port.out.PersonRepository;
import com.reba.challenge.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.reba.challenge.adapter.controller.utils.PersonUtils.validateAge;

@Component
@RequiredArgsConstructor
public class CreatePersonUseCase implements CreatePersonCommand {
    private final PersonRepository personRepository;

    @Override
    public Person execute(Person person) {
        validateAge(person.getBirthDate());
        return personRepository.create(person);
    }

}
