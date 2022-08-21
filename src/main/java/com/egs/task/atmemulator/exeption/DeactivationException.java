package com.egs.task.atmemulator.exeption;

public class DeactivationException extends RuntimeException {
    public DeactivationException(String username) {

        super(String.format("Can`t authorize user. User is deactivated ", username));
    }
}
