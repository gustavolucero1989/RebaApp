package com.reba.challenge.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RelationshipType {
    BROTHER("HERMAN@"),
    UNCLE("TI@"),
    COUSIN("PRIM@"),
    NONE("NO HAY RELACION");

    private final String description;

    public String getDescription() {
        return description;
    }
}
