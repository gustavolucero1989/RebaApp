package com.reba.challenge.adapter.controller.model;

import com.reba.challenge.domain.Person;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PersonBody {
    private String name;
    private String lastName;
    private String documentType;
    private Integer documentNumber;
    private String email;
    private LocalDate birthDate;
    private String country;
    private String nationality;

    public Person toDomain() {
        return Person.builder()
            .name(name)
            .lastName(lastName)
            .documentType(documentType)
            .documentNumber(documentNumber)
            .email(email)
            .birthDate(birthDate)
            .country(country)
            .nationality(nationality)
            .build();
    }
}
