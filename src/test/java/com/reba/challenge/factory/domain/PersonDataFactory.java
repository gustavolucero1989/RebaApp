package com.reba.challenge.factory.domain;

import com.reba.challenge.domain.Person;
import com.reba.challenge.factory.Constant;

import java.time.LocalDate;

public class PersonDataFactory {

    public static Person buildPersonMock() {
        return Person.builder()
            .id(3L)
            .name(Constant.NAME)
            .lastName(Constant.LAST_NAME)
            .documentType(Constant.DOCUMENT_TYPE)
            .documentNumber(Constant.DOCUMENT_NUMBER)
            .email(Constant.EMAIL)
            .birthDate(Constant.BIRTH_DATE)
            .country(Constant.COUNTRY)
            .nationality(Constant.NATIONALITY)
            .idFather(Constant.ID_1)
            .build();
    }

    public static Person buildOtherPersonMock() {
        return Person.builder()
            .id(4L)
            .name(Constant.NAME)
            .lastName(Constant.LAST_NAME)
            .documentType(Constant.DOCUMENT_TYPE)
            .documentNumber(Constant.DOCUMENT_NUMBER)
            .email(Constant.EMAIL)
            .birthDate(Constant.BIRTH_DATE)
            .country(Constant.COUNTRY)
            .nationality(Constant.NATIONALITY)
            .idFather(Constant.ID_1)
            .build();
    }

    public static Person buildPersonNotValidMock() {
        return Person.builder()
            .name(Constant.NAME)
            .lastName(Constant.LAST_NAME)
            .documentType(Constant.DOCUMENT_TYPE)
            .documentNumber(Constant.DOCUMENT_NUMBER)
            .email(Constant.EMAIL)
            .birthDate(LocalDate.of(2022, 1, 1))
            .country(Constant.COUNTRY)
            .nationality(Constant.NATIONALITY)
            .build();
    }

}
