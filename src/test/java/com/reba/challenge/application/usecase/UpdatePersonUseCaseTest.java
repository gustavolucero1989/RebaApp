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

import static com.reba.challenge.utils.PersonUtils.validateAge;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("UpdatePersonUseCase Test")
@ExtendWith(MockitoExtension.class)
class UpdatePersonUseCaseTest {
    @Mock
    private PersonRepository personRepository;

    private UpdatePersonUseCase updatePersonUseCase;
    private Long id1;
    private Person person;

    @BeforeEach
    void setUp() {
        id1 = Constant.ID_1;
        person = PersonDataFactory.buildPersonMock();
        updatePersonUseCase = new UpdatePersonUseCase(personRepository);
    }

    @Test
    @DisplayName("Cuando actualizo una persona con edad válida, entonces espero una persona actualizada")
    void testUpdatePerson() {
        when(personRepository.update(person, id1)).thenReturn(person);

        Person result = updatePersonUseCase.execute(person, id1);

        validateAge(person.getBirthDate());
        verify(personRepository).update(person, id1);
        assertEquals(person, result);
    }

}

