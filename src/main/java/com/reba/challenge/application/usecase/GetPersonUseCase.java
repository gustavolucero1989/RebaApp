package com.reba.challenge.application.usecase;

import com.reba.challenge.application.port.in.GetPersonQuery;
import com.reba.challenge.application.port.out.PersonRepository;
import com.reba.challenge.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetPersonUseCase implements GetPersonQuery {
    private final PersonRepository personRepository;

    @Override
    public Person execute(Long id) {
        return personRepository.get(id);
    }

}
