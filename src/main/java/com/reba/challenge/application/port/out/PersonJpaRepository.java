package com.reba.challenge.application.port.out;

import com.reba.challenge.adapter.jdbc.model.PersonJdbcModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PersonJpaRepository extends JpaRepository<PersonJdbcModel, Long> {
    @Query(value = "SELECT pais AS country, ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM persona), 2) AS percentage FROM persona GROUP BY pais", nativeQuery = true)
    List<Map<String, Object>> getDemography();
}
