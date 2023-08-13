package com.reba.challenge.adapter.jdbc.model;

import com.reba.challenge.domain.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "persona", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tipo_documento", "numero_documento", "pais"})
})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PersonJdbcModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    String name;
    @Column(name = "apellido")
    String lastName;
    @Column(name = "tipo_documento")
    String documentType;
    @Column(name = "numero_documento")
    Integer documentNumber;
    @NotNull
    @Column(name = "email", nullable = false)
    String email;
    @Column(name = "fecha_nacimiento")
    LocalDate birthDate;
    @Column(name = "pais")
    String country;
    @Column(name = "nacionalidad")
    String nationality;

    public static PersonJdbcModel toJdbc(Person person) {
        return PersonJdbcModel.builder()
            .name(person.getName())
            .lastName(person.getLastName())
            .documentType(person.getDocumentType())
            .documentNumber(person.getDocumentNumber())
            .email(person.getEmail())
            .birthDate(person.getBirthDate())
            .country(person.getCountry())
            .nationality(person.getNationality())
            .build();
    }

    public static Person fromDomain(PersonJdbcModel personJdbc) {
        return Person.builder()
            .id(personJdbc.getId())
            .name(personJdbc.getName())
            .lastName(personJdbc.getLastName())
            .documentType(personJdbc.getDocumentType())
            .documentNumber(personJdbc.getDocumentNumber())
            .email(personJdbc.getEmail())
            .birthDate(personJdbc.getBirthDate())
            .country(personJdbc.getCountry())
            .nationality(personJdbc.getNationality())
            .build();
    }

}
