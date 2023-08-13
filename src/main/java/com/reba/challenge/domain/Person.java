package com.reba.challenge.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
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
}
