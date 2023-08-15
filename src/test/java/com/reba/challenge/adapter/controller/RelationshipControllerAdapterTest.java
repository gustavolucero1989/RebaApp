package com.reba.challenge.adapter.controller;

import com.reba.challenge.application.port.in.CreateRelationshipCommand;
import com.reba.challenge.application.port.in.GetRelationshipCommand;
import com.reba.challenge.factory.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("RelationshipControllerAdapter Test")
@ExtendWith(MockitoExtension.class)
class RelationshipControllerAdapterTest {
    @Mock
    private CreateRelationshipCommand createRelationshipCommand;
    @Mock
    private GetRelationshipCommand getRelationshipCommand;

    private RelationshipControllerAdapter adapter;
    private Long id1;
    private Long id2;

    @BeforeEach
    void init() {
        id1 = Constant.ID_1;
        id2 = Constant.ID_2;
        adapter = new RelationshipControllerAdapter(createRelationshipCommand, getRelationshipCommand);
    }

    @Test
    @DisplayName("Cuando intento crear una relación, entonces espero un 201")
    void testCreateRelationship() {
        when(createRelationshipCommand.execute(anyLong(), anyLong())).thenReturn(id1 + " es padre de " + id2);

        ResponseEntity<String> result = adapter.create(id1, id2);

        verify(createRelationshipCommand).execute(anyLong(), anyLong());
        assertEquals(id1 + " es padre de " + id2, result.getBody());
    }

    @Test
    @DisplayName("Cuando intento obtener una relación, entonces espero un 200 OK")
    void testGetRelationship() {
        when(getRelationshipCommand.execute(anyLong(), anyLong())).thenReturn(Constant.BROTHER);

        String result = adapter.get(id1, id2);

        verify(getRelationshipCommand).execute(anyLong(), anyLong());
        assertEquals(Constant.BROTHER, result);
    }
}
