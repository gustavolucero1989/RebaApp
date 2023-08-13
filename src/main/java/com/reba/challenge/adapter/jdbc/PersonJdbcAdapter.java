package com.reba.challenge.adapter.jdbc;

import com.reba.challenge.adapter.jdbc.model.PersonJdbcModel;
import com.reba.challenge.application.port.out.PersonJpaRepository;
import com.reba.challenge.application.port.out.PersonRepository;
import com.reba.challenge.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class PersonJdbcAdapter implements PersonRepository {
    private final PersonJpaRepository personJpaRepository;

    private static final String NOT_FOUND = "No se encontró el objeto";

    @Override
    public Person create(Person person) {
        log.debug("Entrando al metodo: create | person: {}", person);
        PersonJdbcModel responseModel = personJpaRepository.save(PersonJdbcModel.toJdbc(person));
        Person response = PersonJdbcModel.fromDomain(responseModel);
        log.info("Se obtiene del metodo: create | repuesta: {}", response);
        return response;
    }

    @Override
    public Person get(Long id) {
        log.debug("Entrando al metodo: get | id: {}", id);
        Optional<PersonJdbcModel> model = personJpaRepository.findById(id);
        PersonJdbcModel responseModel = model.orElseThrow(() -> new NoSuchElementException(NOT_FOUND));
        Person response = PersonJdbcModel.fromDomain(responseModel);
        log.info("Se obtiene del metodo: get | repuesta: {}", response);
        return response;
    }

    @Override
    public Person update(Person person, Long id) {
        log.debug("Entrando al metodo: update | person: {}", person);
        Optional<PersonJdbcModel> model = personJpaRepository.findById(id);
        PersonJdbcModel jdbcModelToUpdate = model.orElseThrow(() -> new NoSuchElementException(NOT_FOUND));
        setJdbcToUpdate(jdbcModelToUpdate, person);
        PersonJdbcModel responseModel = personJpaRepository.save(jdbcModelToUpdate);
        Person response = PersonJdbcModel.fromDomain(responseModel);
        log.info("Se obtiene del metodo: update | repuesta: {}", response);
        return response;
    }

    @Override
    public void delete(Long id) {
        log.debug("Entrando al metodo: delete | id: {}", id);
        personJpaRepository.deleteById(id);
        log.info("Se borro con exito la entidad con id: {}", id);
    }

    @Override
    public String createRelationship(Long id1, Long id2) {
        log.debug("Entrando al metodo: createRelationship | id1: {} | id2: {}", id1, id2);
        Optional<PersonJdbcModel> model = personJpaRepository.findById(id2);
        PersonJdbcModel jdbcModelToUpdate = model.orElseThrow(() -> new NoSuchElementException(NOT_FOUND));
        jdbcModelToUpdate.setIdFather(id1);
        personJpaRepository.save(jdbcModelToUpdate);
        return id1 + " es padre de " + id2;
    }

    private void setJdbcToUpdate(PersonJdbcModel jdbcModelToUpdate, Person person) {
        jdbcModelToUpdate.setName(person.getName());
        jdbcModelToUpdate.setLastName(person.getLastName());
        jdbcModelToUpdate.setDocumentType(person.getDocumentType());
        jdbcModelToUpdate.setDocumentNumber(person.getDocumentNumber());
        jdbcModelToUpdate.setEmail(person.getEmail());
        jdbcModelToUpdate.setBirthDate(person.getBirthDate());
        jdbcModelToUpdate.setCountry(person.getCountry());
        jdbcModelToUpdate.setNationality(person.getNationality());
    }
}
