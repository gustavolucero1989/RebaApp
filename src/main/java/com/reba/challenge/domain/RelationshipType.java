package com.reba.challenge.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RelationshipType {
    BROTHER("HERMAN@"),
    UNCLE("TI@"),
    COUSIN("PRIM@"),
    NONE("NO EXISTE RELACION");

    private final String description;

    public String getDescription() {
        return description;
    }
}
