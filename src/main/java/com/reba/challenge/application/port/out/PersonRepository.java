package com.reba.challenge.application.port.out;

import com.reba.challenge.domain.Person;

public interface PersonRepository {

    Person create(Person person);

    Person get(Long id);

    Person update(Person person);

    void delete(Long id);

}
