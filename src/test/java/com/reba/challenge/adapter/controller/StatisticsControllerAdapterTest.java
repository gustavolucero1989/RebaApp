package com.reba.challenge.adapter.controller;

import com.reba.challenge.application.port.in.GetDemographicsQuery;
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

@DisplayName("StatisticsControllerAdapter Test")
@ExtendWith(MockitoExtension.class)
class StatisticsControllerAdapterTest {
    @Mock
    private GetDemographicsQuery getDemographicsQuery;

    private StatisticsControllerAdapter adapter;
    private List<Demography> demographicsMock;

    @BeforeEach
    void init() {
        demographicsMock = DemographyDataFactory.buildDemographics();
        adapter = new StatisticsControllerAdapter(getDemographicsQuery);
    }

    @Test
    @DisplayName("Cuando intento obtener estadísticas demográficas, entonces espero un 200 OK")
    void testGetDemographics() {
        when(getDemographicsQuery.execute()).thenReturn(demographicsMock);

        List<Demography> result = adapter.getDemographics();

        verify(getDemographicsQuery).execute();
        assertEquals(demographicsMock, result);
    }
}
