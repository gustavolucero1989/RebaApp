package com.reba.challenge.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;

@Value
@Builder
public class Person {
    Long id;
    String name;
    String lastName;
    String documentType;
    Integer documentNumber;
    String email;
    LocalDate birthDate;
    String country;
    String nationality;
    @With
    Long idFather;
}
