package com.reba.challenge.application.port.in;

import com.reba.challenge.domain.Demography;

import java.util.List;

public interface GetDemographicsQuery {
    List<Demography> execute();
}
