package com.example.visitlyBackend.Exceptions;

public class NoUser extends RuntimeException {

    String msg;

    public NoUser() {
    }

    public NoUser(String msg) {
        super(msg);
        this.msg = msg;
    }

}
