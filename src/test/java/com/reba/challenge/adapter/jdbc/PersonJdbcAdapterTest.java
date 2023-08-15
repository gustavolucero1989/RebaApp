package com.reba.challenge.adapter.jdbc;

import com.reba.challenge.adapter.jdbc.model.PersonJdbcModel;
import com.reba.challenge.application.port.out.PersonJpaRepository;
import com.reba.challenge.domain.Demography;
import com.reba.challenge.domain.Person;
import com.reba.challenge.factory.Constant;
import com.reba.challenge.factory.domain.DemographyDataFactory;
import com.reba.challenge.factory.domain.PersonDataFactory;
import com.reba.challenge.factory.model.PersonModelDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("PersonJdbcAdapter Test")
@ExtendWith(MockitoExtension.class)
class PersonJdbcAdapterTest {
    @Mock
    private PersonJpaRepository personJpaRepository;

    private PersonJdbcAdapter adapter;
    private PersonJdbcModel jdbcModelMock;
    private Person personMock;
    private Long id1;
    private Long id2;
    private Long nonExistentPersonId;
    private List<Demography> demographicsMock;
    private List<Map<String, Object>> resultsMock;

    @BeforeEach
    void init() {
        jdbcModelMock = PersonModelDataFactory.buildPersonJdbcModelMock();
        personMock = PersonDataFactory.buildPersonMock();
        id1 = Constant.ID_1;
        id2 = Constant.ID_2;
        nonExistentPersonId = Constant.NOT_EXISTS_ID;
        demographicsMock = DemographyDataFactory.buildDemographics();
        resultsMock = DemographyDataFactory.buildMapMock();
        adapter = new PersonJdbcAdapter(personJpaRepository);
    }

    @Test
    @DisplayName("Cuando intento crear una persona, entonces espero 200 Ok")
    void testCreatePerson() {
        when(personJpaRepository.save(any(PersonJdbcModel.class))).thenReturn(jdbcModelMock);

        Person result = adapter.create(personMock);

        verify(personJpaRepository).save(any(PersonJdbcModel.class));
        assertEquals(personMock, result);
    }

    @Test
    @DisplayName("Cuando intento obtener una persona por ID, entonces espero 200 Ok")
    void testGetPerson() {
        when(personJpaRepository.findById(id1)).thenReturn(Optional.of(jdbcModelMock));

        Person result = adapter.get(id1);

        verify(personJpaRepository).findById(id1);
        assertEquals(PersonJdbcModel.fromDomain(jdbcModelMock), result);
    }

    @Test
    @DisplayName("Cuando intento actualizar una persona, entonces espero 200 Ok")
    void testUpdatePerson() {
        when(personJpaRepository.findById(id1)).thenReturn(Optional.of(jdbcModelMock));
        when(personJpaRepository.save(any(PersonJdbcModel.class))).thenReturn(jdbcModelMock);

        Person result = adapter.update(personMock, id1);

        verify(personJpaRepository).findById(id1);
        verify(personJpaRepository).save(any(PersonJdbcModel.class));
        assertEquals(PersonJdbcModel.fromDomain(jdbcModelMock), result);
    }

    @Test
    @DisplayName("Cuando intento actualizar una persona que no existe, entonces espero una excepción NoSuchElementException")
    void testUpdateNonExistentPerson() {
        when(personJpaRepository.findById(nonExistentPersonId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> adapter.update(personMock, nonExistentPersonId));
    }

    @Test
    @DisplayName("Cuando intento borrar una persona existente, entonces espero que se borre")
    void testDeleteExistingPerson() {
        Long idToDelete = 3L;
        when(personJpaRepository.findById(idToDelete)).thenReturn(Optional.of(jdbcModelMock));

        adapter.delete(idToDelete);

        verify(personJpaRepository).findById(idToDelete);
        verify(personJpaRepository).deleteById(idToDelete);
    }

    @Test
    @DisplayName("Cuando intento borrar una persona inexistente, entonces espero una excepción NoSuchElementException")
    void testDeleteNonExistingPerson() {
        when(personJpaRepository.findById(id1)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> adapter.delete(id1));

        verify(personJpaRepository).findById(id1);
    }

    @Test
    @DisplayName("Cuando intento crear una relación entre personas, entonces espero un resultado válido")
    void testCreateRelationship() {
        when(personJpaRepository.findById(id1)).thenReturn(Optional.of(jdbcModelMock));
        when(personJpaRepository.findById(id2)).thenReturn(Optional.of(jdbcModelMock));
        when(personJpaRepository.save(any(PersonJdbcModel.class))).thenReturn(jdbcModelMock);

        String result = adapter.createRelationship(id1, id2);

        verify(personJpaRepository).findById(id1);
        verify(personJpaRepository).findById(id2);
        verify(personJpaRepository).save(any(PersonJdbcModel.class));
        assertEquals(id1 + " es padre de " + id2, result);
    }

    @Test
    @DisplayName("Cuando intento crear una relación con una persona inexistente, entonces espero una excepción NoSuchElementException")
    void testCreateRelationshipWithNonExistentPerson() {
        when(personJpaRepository.findById(id1)).thenReturn(Optional.of(jdbcModelMock));
        when(personJpaRepository.findById(nonExistentPersonId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> adapter.createRelationship(id1, nonExistentPersonId));
    }

    @Test
    @DisplayName("Cuando intento obtener estadísticas demográficas, entonces espero un resultado válido")
    void testGetDemographics() {
        when(personJpaRepository.getDemography()).thenReturn(resultsMock);

        List<Demography> result = adapter.getDemographics();

        verify(personJpaRepository).getDemography();
        assertEquals(demographicsMock.size(), result.size());
    }

}
