package com.reba.challenge.adapter.controller;

import com.reba.challenge.application.port.in.GetDemographicsQuery;
import com.reba.challenge.domain.Demography;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/challenge/stats")
@Slf4j
@RequiredArgsConstructor
public class StatisticsControllerAdapter {
    private final GetDemographicsQuery getDemographicsQuery;

    @GetMapping
    public List<Demography> getDemographics() {
        log.debug("Entrando al endpoint: getDemographics | uri: /api/v1/challenge/stats");
        return getDemographicsQuery.execute();
    }
}
