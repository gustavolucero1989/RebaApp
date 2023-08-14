package com.reba.challenge.factory.model;

import com.reba.challenge.adapter.controller.model.PersonBody;
import com.reba.challenge.adapter.jdbc.model.PersonJdbcModel;
import com.reba.challenge.factory.Constant;

public class PersonModelDataFactory {

    public static PersonBody buildPersonBodyMock() {
        return PersonBody.builder()
            .name(Constant.NAME)
            .lastName(Constant.LAST_NAME)
            .documentType(Constant.DOCUMENT_TYPE)
            .documentNumber(Constant.DOCUMENT_NUMBER)
            .email(Constant.EMAIL)
            .birthDate(Constant.BIRTH_DATE)
            .country(Constant.COUNTRY)
            .nationality(Constant.NATIONALITY)
            .build();
    }

    public static PersonJdbcModel buildPersonJdbcModelMock() {
        return PersonJdbcModel.builder()
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

}
