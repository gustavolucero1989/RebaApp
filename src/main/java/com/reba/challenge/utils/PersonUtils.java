package com.reba.challenge.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Period;

@UtilityClass
@Slf4j
public class PersonUtils {
    private static final int MIN_AGE = 18;

    public static void validateAge(LocalDate birthDate) {
        log.debug("Entrando al metodo: validateAge | Fecha de nacimiento: {}", birthDate);
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < MIN_AGE) {
            throw new IllegalArgumentException("La persona debe tener al menos 18 años de edad.");
        }
    }
}
