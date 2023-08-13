package com.reba.challenge.application.port.in;

public interface CreateRelationshipCommand {
    String execute(Long id1, Long id2);
}
