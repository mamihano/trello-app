package com.test.trello.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String messages) {
        super(messages);
    }
}
