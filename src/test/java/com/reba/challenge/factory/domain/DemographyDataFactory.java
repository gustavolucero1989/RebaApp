package com.reba.challenge.factory.domain;

import com.reba.challenge.domain.Demography;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemographyDataFactory {
    public static List<Demography> buildDemographics() {
        return Arrays.asList(
            Demography.builder().country("Country 1").percentage(25.0).build(),
            Demography.builder().country("Country 2").percentage(45.0).build()
        );
    }

    public static List<Map<String, Object>> buildMapMock() {
        List<Map<String, Object>> resultsMock = new ArrayList<>();

        Map<String, Object> mockMap1 = new HashMap<>();
        mockMap1.put("COUNTRY", "Country 1");
        mockMap1.put("PERCENTAGE", BigDecimal.valueOf(25.0));
        resultsMock.add(mockMap1);

        Map<String, Object> mockMap2 = new HashMap<>();
        mockMap2.put("COUNTRY", "Country 2");
        mockMap2.put("PERCENTAGE", BigDecimal.valueOf(45.0));
        resultsMock.add(mockMap2);

        return resultsMock;

    }

}
