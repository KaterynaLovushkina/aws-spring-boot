package com.lovushkina.store.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String tableName, Integer id) {
        super(String.format("Can`t find table %s with id: %d ", tableName, id));
    }

    public DataNotFoundException(String tableName, String id) {
        super(String.format("Can`t find table %s with id: %d ", tableName, id));
    }
}
