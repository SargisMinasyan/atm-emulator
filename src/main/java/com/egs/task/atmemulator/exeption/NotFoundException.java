package com.egs.task.atmemulator.exeption;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String id) {

        super(String.format("No data found ", id));
    }
}