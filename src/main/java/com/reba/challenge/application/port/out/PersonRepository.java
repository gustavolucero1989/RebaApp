package com.reba.challenge.application.port.out;

import com.reba.challenge.domain.Demography;
import com.reba.challenge.domain.Person;

import java.util.List;

public interface PersonRepository {

    Person create(Person person);

    Person get(Long id);

    Person update(Person person, Long id);

    void delete(Long id);

    String createRelationship(Long id1, Long id2);

    List<Demography> getDemographics();

}
