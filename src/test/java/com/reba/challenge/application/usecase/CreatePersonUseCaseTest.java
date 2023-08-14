package com.reba.challenge.application.usecase;

import com.reba.challenge.application.port.out.PersonRepository;
import com.reba.challenge.domain.Person;
import com.reba.challenge.factory.domain.PersonDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.reba.challenge.utils.PersonUtils.validateAge;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@DisplayName("CreatePersonUseCaseTest Test")
@ExtendWith(MockitoExtension.class)
class CreatePersonUseCaseTest {
    @Mock
    private PersonRepository personRepository;

    private CreatePersonUseCase createPersonUseCase;
    private Person person;
    private Person personNotValid;

    @BeforeEach
    void setUp() {
        person = PersonDataFactory.buildPersonMock();
        personNotValid = PersonDataFactory.buildPersonNotValidMock();
        createPersonUseCase = new CreatePersonUseCase(personRepository);
    }

    @Test
    @DisplayName("Cuando creo una persona con edad válida, entonces espero una persona creada")
    void testCreatePersonWithValidAge() {
        when(personRepository.create(any(Person.class))).thenReturn(person);

        Person result = createPersonUseCase.execute(person);

        validateAge(person.getBirthDate());
        verify(personRepository).create(person);
        assertEquals(person, result);
    }

    @Test
    @DisplayName("Cuando creo una persona con edad no válida, entonces espero una excepción")
    void testCreatePersonWithInvalidAge() {
        assertThrows(IllegalArgumentException.class, () -> {
            validateAge(personNotValid.getBirthDate());
            createPersonUseCase.execute(personNotValid);
        });
        verifyNoInteractions(personRepository);
    }
}
