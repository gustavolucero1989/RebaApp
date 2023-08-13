package com.reba.challenge.application.port.in;

import com.reba.challenge.domain.Person;

public interface UpdatePersonCommand {
    Person execute(Person person, Long id);
}
