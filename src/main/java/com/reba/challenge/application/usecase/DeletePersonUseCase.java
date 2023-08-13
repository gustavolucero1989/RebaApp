package com.reba.challenge.application.usecase;

import com.reba.challenge.application.port.in.DeletePersonCommand;
import com.reba.challenge.application.port.out.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletePersonUseCase implements DeletePersonCommand {
    private final PersonRepository personRepository;

    @Override
    public void execute(Long id) {
        personRepository.delete(id);
    }

}
