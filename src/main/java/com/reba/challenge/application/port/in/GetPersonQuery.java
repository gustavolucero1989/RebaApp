package com.reba.challenge.application.port.in;

import com.reba.challenge.domain.Person;

public interface GetPersonQuery {
    Person execute(Long id);
}
