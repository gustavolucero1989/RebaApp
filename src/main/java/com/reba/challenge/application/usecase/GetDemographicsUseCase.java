package com.reba.challenge.application.usecase;

import com.reba.challenge.application.port.in.GetDemographicsQuery;
import com.reba.challenge.application.port.out.PersonRepository;
import com.reba.challenge.domain.Demography;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetDemographicsUseCase implements GetDemographicsQuery {
    private final PersonRepository personRepository;

    @Override
    public List<Demography> execute() {
        return personRepository.getDemographics();
    }
}
