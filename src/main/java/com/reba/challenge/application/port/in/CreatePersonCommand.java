package com.reba.challenge.application.port.in;

import com.reba.challenge.domain.Person;

public interface CreatePersonCommand {
    Person execute(Person person);
}
