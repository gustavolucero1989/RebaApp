package com.reba.challenge.adapter.jdbc;

import com.reba.challenge.adapter.jdbc.model.PersonJdbcModel;
import com.reba.challenge.application.port.out.PersonJpaRepository;
import com.reba.challenge.application.port.out.PersonRepository;
import com.reba.challenge.domain.Demography;
import com.reba.challenge.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class PersonJdbcAdapter implements PersonRepository {
    private final PersonJpaRepository personJpaRepository;

    private static final String NOT_FOUND = "No se encontró el objeto";
    private static final String COUNTRY = "country";
    private static final String PERCENTAGE = "percentage";


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
        log.info("Se borro con exito la entidad | id: {}", id);
    }

    @Override
    public String createRelationship(Long id1, Long id2) {
        log.debug("Entrando al metodo: createRelationship | id1: {} | id2: {}", id1, id2);
        Optional<PersonJdbcModel> model = personJpaRepository.findById(id2);
        PersonJdbcModel jdbcModelToUpdate = model.orElseThrow(() -> new NoSuchElementException(NOT_FOUND));
        jdbcModelToUpdate.setIdFather(id1);
        personJpaRepository.save(jdbcModelToUpdate);
        log.info("Se obtiene del metodo: createRelationship | relacion: id1 = {}, id2 = {}", id1, id2);
        return id1 + " es padre de " + id2;
    }

    @Override
    public List<Demography> getDemographics() {
        log.debug("Entrando al metodo: getDemographics");
        List<Map<String, Object>> results = personJpaRepository.getDemography();
        List<Demography> demographics = fromMapList(results);
        log.info("Se obtiene del metodo: getDemographics | size: {}", demographics.size());
        return demographics;
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
        jdbcModelToUpdate.setIdFather(person.getIdFather());
    }

    public static List<Demography> fromMapList(List<Map<String, Object>> mapList) {
        return mapList.stream()
            .map(result -> {
                String country = (String) result.get(COUNTRY);
                BigDecimal percentage = (BigDecimal) result.get(PERCENTAGE);
                return Demography.builder()
                    .country(country)
                    .percentage(percentage.doubleValue())
                    .build();
            })
            .collect(Collectors.toList());
    }

}
