package com.reba.challenge.adapter.controller;

import com.reba.challenge.application.port.in.GetDemographicsQuery;
import com.reba.challenge.domain.Demography;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Statistics", description = "Statistics controller")
public class StatisticsControllerAdapter {
    private final GetDemographicsQuery getDemographicsQuery;

    @Operation(summary = "Get Demographics", description = "It retrieves the country-wise demographics in percentages.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @GetMapping
    public List<Demography> getDemographics() {
        log.debug("Entrando al endpoint: getDemographics | uri: /api/v1/challenge/stats");
        return getDemographicsQuery.execute();
    }
}
