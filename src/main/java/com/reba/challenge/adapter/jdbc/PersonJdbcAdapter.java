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
        PersonJdbcModel responseModel = model.orElseThrow(() -> new NoSuchElementException("No se encontró el objeto"));
        Person response = PersonJdbcModel.fromDomain(responseModel);
        log.info("Se obtiene del metodo: get | repuesta: {}", response);
        return response;
    }

    @Override
    public Person update(Person person) {
        log.debug("Entrando al metodo: update | person: {}", person);
        Optional<PersonJdbcModel> model = personJpaRepository.findById(person.getId());
        PersonJdbcModel jdbcModelToUpdate = model.orElseThrow(() -> new NoSuchElementException("No se encontró el objeto"));
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
