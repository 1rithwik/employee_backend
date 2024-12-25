package com.example.visitlyBackend.Exceptions;

public class InvalidInput extends RuntimeException {

    String msg;

    public InvalidInput() {
    }

    public InvalidInput(String msg) {
        super(msg);
        this.msg = msg;
    }
}
