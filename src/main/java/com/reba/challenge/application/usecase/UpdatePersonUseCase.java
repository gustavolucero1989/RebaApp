package com.reba.challenge.application.usecase;

import com.reba.challenge.application.port.in.UpdatePersonCommand;
import com.reba.challenge.application.port.out.PersonRepository;
import com.reba.challenge.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.reba.challenge.adapter.controller.utils.PersonUtils.validateAge;

@Component
@RequiredArgsConstructor
public class UpdatePersonUseCase implements UpdatePersonCommand {
    private final PersonRepository personRepository;

    @Override
    public Person execute(Person person, Long id) {
        validateAge(person.getBirthDate());
        return personRepository.update(person, id);
    }

}
