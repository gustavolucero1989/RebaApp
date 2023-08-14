package com.reba.challenge.application.usecase;

import com.reba.challenge.application.port.out.PersonRepository;
import com.reba.challenge.domain.Demography;
import com.reba.challenge.factory.domain.DemographyDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("GetDemographicsUseCase Test")
@ExtendWith(MockitoExtension.class)
class GetDemographicsUseCaseTest {
    @Mock
    private PersonRepository personRepository;

    private GetDemographicsUseCase getDemographicsUseCase;
    List<Demography> demographics;

    @BeforeEach
    void setUp() {
        demographics = DemographyDataFactory.buildDemographics();
        getDemographicsUseCase = new GetDemographicsUseCase(personRepository);
    }

    @Test
    @DisplayName("Cuando obtengo estadísticas demográficas, entonces espero un resultado válido")
    void testGetDemographics() {
        when(personRepository.getDemographics()).thenReturn(demographics);

        List<Demography> result = getDemographicsUseCase.execute();

        verify(personRepository).getDemographics();
        assertEquals(demographics, result);
    }
}






