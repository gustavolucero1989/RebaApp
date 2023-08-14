package com.reba.challenge.adapter.controller;

import com.reba.challenge.adapter.controller.model.PersonBody;
import com.reba.challenge.application.port.in.CreatePersonCommand;
import com.reba.challenge.application.port.in.DeletePersonCommand;
import com.reba.challenge.application.port.in.GetPersonQuery;
import com.reba.challenge.application.port.in.UpdatePersonCommand;
import com.reba.challenge.domain.Person;
import com.reba.challenge.factory.Constant;
import com.reba.challenge.factory.domain.PersonDataFactory;
import com.reba.challenge.factory.model.PersonModelDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("PersonControllerAdapter Test")
@ExtendWith(MockitoExtension.class)
class PersonControllerAdapterTest {
    @Mock
    private CreatePersonCommand createPersonCommand;
    @Mock
    private GetPersonQuery getPersonQuery;
    @Mock
    private UpdatePersonCommand updatePersonCommand;
    @Mock
    private DeletePersonCommand deletePersonCommand;

    private PersonControllerAdapter adapter;
    private Person personMock;
    private PersonBody personBodyMock;

    @BeforeEach
    void init() {
        personMock = PersonDataFactory.buildPersonMock();
        personBodyMock = PersonModelDataFactory.buildPersonBodyMock();

        adapter = new PersonControllerAdapter(createPersonCommand, getPersonQuery, updatePersonCommand, deletePersonCommand);
    }

    @Test
    @DisplayName("Cuando intento crear una persona,entonces espero un 200 OK")
    void testCreatePerson() {
        when(createPersonCommand.execute(any(Person.class))).thenReturn(personMock);

        Person result = adapter.create(personBodyMock);

        verify(createPersonCommand).execute(any(Person.class));
        assertEquals(personMock, result);
    }

    @Test
    @DisplayName("Cuando intento obtener una persona,entonces espero un 200 OK")
    void testGetPerson() {
        when(getPersonQuery.execute(anyLong())).thenReturn(personMock);

        Person result = adapter.get(anyLong());

        verify(getPersonQuery).execute(anyLong());
        assertEquals(personMock, result);
    }

    @Test
    @DisplayName("Cuando intento actualizar una persona,entonces espero un 200 OK")
    void testUpdatePerson() {
        when(updatePersonCommand.execute(any(Person.class), anyLong())).thenReturn(personMock);

        Person result = adapter.update(personBodyMock, Constant.ID_1);

        verify(updatePersonCommand).execute(any(Person.class), anyLong());
        assertEquals(personMock, result);
    }

    @Test
    @DisplayName("Cuando intento eliminar una persona, entonces espero un 200 OK")
    void testDeletePerson() {
        doNothing().when(deletePersonCommand).execute(anyLong());

        adapter.delete(anyLong());

        verify(deletePersonCommand).execute(anyLong());
    }

}
