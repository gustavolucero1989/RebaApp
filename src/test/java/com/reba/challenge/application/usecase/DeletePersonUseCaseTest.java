package com.reba.challenge.application.usecase;

import com.reba.challenge.application.port.out.PersonRepository;
import com.reba.challenge.factory.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@DisplayName("DeletePersonUseCase Test")
@ExtendWith(MockitoExtension.class)
class DeletePersonUseCaseTest {
    @Mock
    private PersonRepository personRepository;

    private DeletePersonUseCase deletePersonUseCase;
    private Long id1;

    @BeforeEach
    void setUp() {
        id1 = Constant.ID_1;
        deletePersonUseCase = new DeletePersonUseCase(personRepository);
    }

    @Test
    @DisplayName("Cuando elimino una persona, entonces espero que se llame al método de eliminación en el repositorio")
    void testDeletePerson() {

        deletePersonUseCase.execute(id1);

        verify(personRepository).delete(id1);
    }
}
