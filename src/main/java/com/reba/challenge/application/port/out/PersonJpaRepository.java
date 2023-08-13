package com.reba.challenge.application.port.out;

import com.reba.challenge.adapter.jdbc.model.PersonJdbcModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonJpaRepository extends JpaRepository<PersonJdbcModel, Long> {
}
