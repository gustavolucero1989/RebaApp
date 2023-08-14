package com.reba.challenge.application.usecase;

import com.reba.challenge.application.port.out.PersonRepository;
import com.reba.challenge.domain.Person;
import com.reba.challenge.domain.RelationshipType;
import com.reba.challenge.factory.Constant;
import com.reba.challenge.factory.domain.PersonDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@DisplayName("GetRelationshipUseCase Test")
@ExtendWith(MockitoExtension.class)
class GetRelationshipUseCaseTest {
    @Mock
    private PersonRepository personRepository;

    private GetRelationshipUseCase getRelationshipUseCase;
    private Long id1;
    private Long id2;
    private Person person;
    private Person otherPerson;

    @BeforeEach
    void setUp() {
        id1 = Constant.ID_1;
        id2 = Constant.ID_2;
        person = PersonDataFactory.buildPersonMock();
        otherPerson = PersonDataFactory.buildOtherPersonMock();
        getRelationshipUseCase = new GetRelationshipUseCase(personRepository);
    }

    @Test
    @DisplayName("Cuando obtengo una relación válida, entonces espero un resultado válido")
    void testGetRelationshipNoneValid() {
        String expectedRelationship = RelationshipType.NONE.getDescription();
        when(personRepository.get(id1)).thenReturn(person);
        when(personRepository.get(id2)).thenReturn(person);

        String result = getRelationshipUseCase.execute(id1, id2);

        assertEquals(expectedRelationship, result);
    }

    @Test
    @DisplayName("Cuando obtengo una relación válida, entonces espero un resultado válido")
    void testGetRelationshipBrotherValid() {
        String expectedRelationship = RelationshipType.BROTHER.getDescription();
        when(personRepository.get(id1)).thenReturn(person);
        when(personRepository.get(id2)).thenReturn(otherPerson);

        String result = getRelationshipUseCase.execute(id1, id2);

        assertEquals(expectedRelationship, result);
    }

    @Test
    @DisplayName("Cuando obtengo una relación con la misma persona, entonces espero una excepción")
    void testGetRelationshipSamePerson() {
        assertThrows(IllegalArgumentException.class, () -> getRelationshipUseCase.execute(id1, id1));
        verifyNoInteractions(personRepository);
    }
}

