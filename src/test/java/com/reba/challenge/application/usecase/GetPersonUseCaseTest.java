package com.reba.challenge.application.usecase;

import com.reba.challenge.application.port.out.PersonRepository;
import com.reba.challenge.domain.Person;
import com.reba.challenge.factory.Constant;
import com.reba.challenge.factory.domain.PersonDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("GetPersonUseCase Test")
@ExtendWith(MockitoExtension.class)
class GetPersonUseCaseTest {
    @Mock
    private PersonRepository personRepository;

    private GetPersonUseCase getPersonUseCase;
    private Person personMock;
    private Long id1;

    @BeforeEach
    void setUp() {
        personMock = PersonDataFactory.buildPersonMock();
        id1 = Constant.ID_1;
        getPersonUseCase = new GetPersonUseCase(personRepository);
    }

    @Test
    @DisplayName("Cuando intento obtener una persona, entonces espero un resultado válido")
    void testGetPerson() {
        when(personRepository.get(id1)).thenReturn(personMock);

        Person result = getPersonUseCase.execute(id1);

        verify(personRepository).get(id1);
        assertEquals(personMock, result);
    }
}
