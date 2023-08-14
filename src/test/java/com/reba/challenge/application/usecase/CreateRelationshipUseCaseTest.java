package com.reba.challenge.application.usecase;

import com.reba.challenge.application.port.out.PersonRepository;
import com.reba.challenge.factory.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("CreateRelationshipUseCase Test")
@ExtendWith(MockitoExtension.class)
class CreateRelationshipUseCaseTest {
    @Mock
    private PersonRepository personRepository;

    private CreateRelationshipUseCase createRelationshipUseCase;
    private Long id1;
    private Long id2;

    @BeforeEach
    void setUp() {
        id1 = Constant.ID_1;
        id2 = Constant.ID_2;
        createRelationshipUseCase = new CreateRelationshipUseCase(personRepository);
    }

    @Test
    @DisplayName("Cuando creo una relación, entonces espero un resultado válido")
    void testCreateRelationship() {
        String expectedResult = "1 es padre de 2";

        when(personRepository.createRelationship(id1, id2)).thenReturn(expectedResult);

        String result = createRelationshipUseCase.execute(id1, id2);

        verify(personRepository).createRelationship(id1, id2);
        assertEquals(expectedResult, result);
    }
}
